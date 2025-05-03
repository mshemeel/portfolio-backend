package com.shemeel.portfolio.config;

import com.shemeel.portfolio.model.ApiKey;
import com.shemeel.portfolio.repository.ApiKeyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialDataConfig {

    private final ApiKeyRepository apiKeyRepository;

    @PostConstruct
    public void init() {
        // Create a default API key if none exists
        if (apiKeyRepository.count() == 0) {
            ApiKey defaultApiKey = ApiKey.builder()
                    .key("default-api-key-123")
                    .name("Default API Key")
                    .description("Default API key for portfolio website")
                    .active(true)
                    .build();
            apiKeyRepository.save(defaultApiKey);
            System.out.println("Default API key created: " + defaultApiKey.getKey());
        }
    }
} 