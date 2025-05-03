package com.shemeel.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Header entity representing the header section data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "header")
public class Header extends BaseEntity {
    
    private String name;
    
    private String logoLink;
    
    private List<NavItem> navItems;
    
    /**
     * Inner class representing a navigation item
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class NavItem {
        private String id;
        private String label;
        private String href;
    }
} 