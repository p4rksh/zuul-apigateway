package com.example.apigateway;

import com.example.apigateway.filters.endpoint.RoutingFilter;
import com.example.apigateway.filters.incoming.AuthFilter;
import com.example.apigateway.filters.incoming.PreFilter;
import com.example.apigateway.filters.outcoming.ErrorFilter;
import com.example.apigateway.filters.outcoming.PostFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ApigatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayApplication.class, args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RoutingFilter routeFilter() {
        return new RoutingFilter();
    }
}
