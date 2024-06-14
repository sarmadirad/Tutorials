package com.example.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter(){
        super(Config.class);
    }
    final Logger logger =
            LoggerFactory.getLogger(CustomFilter.class);
    @Override
    public GatewayFilter apply(Config config) {

        //Custom Pre Filter with order.
       return new OrderedGatewayFilter((exchange, chain) -> {
           if(config.preLogger)
           logger.info("Pre Filter executed");
           exchange.getRequest().mutate().header("ServiceB-Request-Header","B400");
           if(config.postLogger)
           exchange.getResponse().getHeaders().add("ServiceB-Request-Header","B400");
           return chain.filter(exchange);
       },1);
    }



    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
