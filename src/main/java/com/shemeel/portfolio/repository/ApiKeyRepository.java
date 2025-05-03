package com.shemeel.portfolio.repository;

import com.shemeel.portfolio.model.ApiKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends MongoRepository<ApiKey, String> {
    Optional<ApiKey> findByKey(String key);
    boolean existsByKey(String key);
} 