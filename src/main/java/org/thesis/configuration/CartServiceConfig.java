package org.thesis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartServiceConfig {

    @Value("${cart.microservice.url}")
    private String cartMicroserviceUrl;

    @Bean
    public String cartMicroserviceUrl() {
        return cartMicroserviceUrl;
    }
}




