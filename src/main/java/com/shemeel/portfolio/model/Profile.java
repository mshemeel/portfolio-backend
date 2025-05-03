package com.shemeel.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Profile entity representing the main user profile information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "profiles")
public class Profile extends BaseEntity {
    
    private String name;
    
    private String title;
    
    private String email;
    
    private String location;
    
    private String avatarUrl;
    
    private String bio;
    
    private String resumeUrl;
} 