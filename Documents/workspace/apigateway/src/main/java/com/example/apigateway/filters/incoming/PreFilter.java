package com.example.apigateway.filters.incoming;

import com.example.apigateway.constants.ZuulFilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * PreFilter
 *
 * request를 가장 처음에 받는 Filter.
 * 로그 관리, 인증/인가 관리 등 api 서버로 proxy하기 전 검증 및 처리 해야하는 작업들을 수행.
 */
public class PreFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return ZuulFilterConstants.PRE_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("############# PreFilter #############");
        logger.info("Request URL : " + RequestContext.getCurrentContext().getRequest().getRequestURL());

        return null;
    }
}
