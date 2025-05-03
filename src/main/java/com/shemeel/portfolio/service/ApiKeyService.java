package com.shemeel.portfolio.service;

import com.shemeel.portfolio.model.ApiKey;

import java.util.List;
import java.util.Optional;

public interface ApiKeyService {
    ApiKey createApiKey(ApiKey apiKey);
    List<ApiKey> getAllApiKeys();
    Optional<ApiKey> getApiKeyById(String id);
    Optional<ApiKey> getApiKeyByKey(String key);
    ApiKey updateApiKey(ApiKey apiKey);
    void deleteApiKey(String id);
    boolean validateApiKey(String key);
} 