package com.shemeel.portfolio.repository;

import com.shemeel.portfolio.model.Footer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Footer entity
 */
@Repository
public interface FooterRepository extends MongoRepository<Footer, String> {
} 