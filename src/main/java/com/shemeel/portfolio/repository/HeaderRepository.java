package com.shemeel.portfolio.repository;

import com.shemeel.portfolio.model.Header;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Header entity
 */
@Repository
public interface HeaderRepository extends MongoRepository<Header, String> {
} 