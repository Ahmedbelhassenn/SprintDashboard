package com.example.sprintdash.JiraApi;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
@Configuration
public class FeignConfig {
    @Value("${feign.client.username}")
    private String username;
    @Value("${feign.client.api-token}")
    private String apiToken;
    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return requestTemplate -> {
            String authHeader = "Basic " +
                    Base64.getEncoder().encodeToString((username + ":" + apiToken).getBytes());
            requestTemplate.header("Authorization", authHeader);
        };
    }
}
