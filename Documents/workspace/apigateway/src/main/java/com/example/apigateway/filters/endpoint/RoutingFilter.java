package com.example.apigateway.filters.endpoint;

import com.example.apigateway.constants.ZuulFilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * RoutingFilter
 *
 * Proxy 작업을 수행하는 Filter.
 */
public class RoutingFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return ZuulFilterConstants.ROUTING_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
