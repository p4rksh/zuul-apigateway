package com.example.apigateway.constants;

public class ZuulFilterConstants {

    /**
     * Filter Order 정의(호출 순서)
     *
     * 1. PreFilter
     * 2. AuthFilter
     * 3. RoutingFilter
     * 4. PostFilter
     * 5. ErrorFilter
     */
    public static final int PRE_ORDER = 1;
    public static final int AUTH_ORDER = 2;
    public static final int ROUTING_ORDER = 10;
    public static final int POST_ORDER = 15;
    public static final int ERROR_ORDER = 20;
}
