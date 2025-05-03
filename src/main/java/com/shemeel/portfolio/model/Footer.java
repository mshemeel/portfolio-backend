package com.shemeel.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Footer entity representing the footer section data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "footer")
public class Footer extends BaseEntity {
    
    private String copyright;
    
    private List<SocialLink> socialLinks;
    
    /**
     * Inner class representing a social media link
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SocialLink {
        private String id;
        private String name;
        private String url;
        private String icon;
    }
} 