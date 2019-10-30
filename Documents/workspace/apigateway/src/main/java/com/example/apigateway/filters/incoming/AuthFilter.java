package com.example.apigateway.filters.incoming;

import com.example.apigateway.constants.ReturnCode;
import com.example.apigateway.constants.ZuulFilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * AuthFilter
 *
 * 인증 서버가 따로 존재할 경우 해당 서버로 요청을 보내 인증을 수행하는 Custom Filter
 * RestTemplate을 사용하여 인증서버의 인증 api 호출
 *
 * 인증 실패 시, 로그를 남기는 작업이 필요하다면 구현.
 */
public class AuthFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Value("${auth.scheme}")
    private String authScheme;

    @Value("${auth.host}")
    private String authHost;

    @Value("${auth.port}")
    private int authPort;

    @Value("${auth.path}")
    private String authPath;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return ZuulFilterConstants.AUTH_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("############# AuthFilter #############");

        HttpServletRequest request = RequestContext.
                getCurrentContext().
                getRequest();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        // 인증서버에서 요구하는 header setting
        httpHeaders.add("x-auth-method", request.getMethod());
        httpHeaders.add("x-auth-request-uri", request.getRequestURI());
        httpHeaders.add("x-auth-query-string", request.getQueryString());
        httpHeaders.add("x-auth-host", request.getHeader("host"));

        List<String> originHeaders = Collections.list(request.getHeaderNames());
        for (String originHeaderKey : originHeaders) {
            httpHeaders.add(originHeaderKey, request.getHeader(originHeaderKey));
        }

        RestTemplate restTemplate = new RestTemplate();
        // 인증서버 인증 api url 빌드
        UriComponents authUrl = UriComponentsBuilder.newInstance()
                                                    .scheme(authScheme)
                                                    .host(authHost)
                                                    .port(authPort)
                                                    .path(authPath)
                                                    .build();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        try {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(authUrl.toString(), httpEntity, String.class);

            logger.info("response status : " + responseEntity.getStatusCodeValue());

            if (responseEntity.getStatusCodeValue() != ReturnCode.SUCCESS.getReturnCode()) {
                throw new ZuulException(ReturnCode.AUTH_FAIL.getReturnMessage(),
                                        ReturnCode.AUTH_FAIL.getReturnCode(),
                                        responseEntity.toString());
            }
        } catch (ZuulException e) {
            /**
             * 인증 실패 사유 로깅 필요 시 작성
             * */

            logger.error("Authentication fail");

            throw e;
        } catch (Exception e) {
            /**
             * 인증 서버 통신 실패 사유 로깅 필요시 작성
             * */

            logger.error("Auth server access fail");

            throw e;
        }

        return null;
    }
}
