package com.shemeel.portfolio.service.impl;

import com.shemeel.portfolio.model.ApiKey;
import com.shemeel.portfolio.repository.ApiKeyRepository;
import com.shemeel.portfolio.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKey createApiKey(ApiKey apiKey) {
        return apiKeyRepository.save(apiKey);
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepository.findAll();
    }

    @Override
    public Optional<ApiKey> getApiKeyById(String id) {
        return apiKeyRepository.findById(id);
    }

    @Override
    public Optional<ApiKey> getApiKeyByKey(String key) {
        return apiKeyRepository.findByKey(key);
    }

    @Override
    public ApiKey updateApiKey(ApiKey apiKey) {
        return apiKeyRepository.save(apiKey);
    }

    @Override
    public void deleteApiKey(String id) {
        apiKeyRepository.deleteById(id);
    }

    @Override
    public boolean validateApiKey(String key) {
        return apiKeyRepository.findByKey(key)
                .map(ApiKey::isActive)
                .orElse(false);
    }
} 