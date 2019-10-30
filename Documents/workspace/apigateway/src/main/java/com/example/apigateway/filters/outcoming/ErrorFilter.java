package com.example.apigateway.filters.outcoming;

import com.example.apigateway.constants.ZuulFilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletResponse;

/**
 * ErrorFilter
 *
 * Filter들을 수행 중에 Error가 발생하였을 때 수행되는 Filter.
 */
public class ErrorFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return ZuulFilterConstants.ERROR_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /**
         * 에러 로그 및 에러 처리 로직
         */
        logger.info("############# ErrorFilter #############");

        HttpServletResponse response = RequestContext.
                getCurrentContext().
                getResponse();

        logger.info("Error code : " + response.getStatus());

        return null;
    }
}
