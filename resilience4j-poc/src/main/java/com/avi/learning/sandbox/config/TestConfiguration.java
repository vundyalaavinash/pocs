package com.avi.learning.sandbox.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri("https://dummyjson.com").build();
    }
}
