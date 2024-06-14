package com.example.apigateway.config;

import com.example.apigateway.filter.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    CustomFilter customFilter;
    @Bean
    public RouteLocator routeBuilder(RouteLocatorBuilder routeLocatorBuilder){
        CustomFilter.Config config=null;
        OrderedGatewayFilter orderedGatewayFilter =
                new OrderedGatewayFilter(customFilter.apply(config), 0);
        return routeLocatorBuilder.routes()

                .route("Microservice-1",r-> r.path("/serviceB/**")
                        .filters(f-> f.filter(customFilter.apply(new CustomFilter.Config())))
                        .uri("http://localhost:8080/getAllEmployee"))

                        //Pre and Post Filters provided by Spring Cloud Gateway
                    /*    .filters(f-> f.addRequestHeader("ServiceB-Request-Header","B100")
                                .addResponseHeader("c","B200"))*/


                .route("Microservice-2",r-> r.path("/serviceA/**")
                        .uri("http://localhost:8081/redis/getAllBook"))
                        .build();
    }
}
