package org.thesis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemsServiceConfig {

    @Value("${items.microservice.url}")
    private String itemsMicroserviceUrl;

    @Bean
    public String itemsMicroserviceUrl() {
        return itemsMicroserviceUrl;
    }
}
