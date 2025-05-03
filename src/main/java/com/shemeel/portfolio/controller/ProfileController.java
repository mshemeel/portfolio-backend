package com.shemeel.portfolio.controller;

import com.shemeel.portfolio.model.Profile;
import com.shemeel.portfolio.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Profile endpoints
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Profile", description = "Profile API")
public class ProfileController {

    private final ProfileService profileService;

    /**
     * Get the profile data
     * @return the profile data
     */
    @Operation(
        summary = "Get profile data",
        description = "Retrieves the main profile information"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Profile data found",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Profile.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Profile data not found",
            content = @Content
        )
    })
    @GetMapping("/profile")
    public ResponseEntity<Profile> getProfile() {
        return profileService.getProfile()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Get a profile by ID
     * @param id the profile ID
     * @return the profile data
     */
    @Operation(
        summary = "Get profile by ID",
        description = "Retrieves profile information by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Profile found",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Profile.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Profile not found",
            content = @Content
        )
    })
    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable String id) {
        return profileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Get all profiles",
        description = "Retrieves all profile information"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Profiles found",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Profile[].class)
            )
        )
    })
    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }
} 