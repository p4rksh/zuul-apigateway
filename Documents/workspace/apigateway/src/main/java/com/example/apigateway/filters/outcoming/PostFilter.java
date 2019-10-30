package com.example.apigateway.filters.outcoming;

import com.example.apigateway.constants.ZuulFilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * PostFilter
 *
 * Proxy 이후 response를 return하는 Filter.
 */
public class PostFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return ZuulFilterConstants.POST_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /**
         * response를 가공하는 로직.
         */
        logger.info("############# PostFilter #############");

        return null;
    }
}
