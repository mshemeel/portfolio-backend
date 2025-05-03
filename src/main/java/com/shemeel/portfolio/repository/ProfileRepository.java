package com.shemeel.portfolio.repository;

import com.shemeel.portfolio.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Profile entity
 */
@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
} 