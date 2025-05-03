package com.shemeel.portfolio.controller;

import com.shemeel.portfolio.model.Header;
import com.shemeel.portfolio.service.HeaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Header endpoints
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Header", description = "Header API")
public class HeaderController {

    private final HeaderService headerService;

    /**
     * Get the header data
     * @return the header data
     */
    @Operation(
        summary = "Get header data",
        description = "Retrieves the header information including navigation items"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Header data found",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Header.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Header data not found",
            content = @Content
        )
    })
    @GetMapping("/header")
    public ResponseEntity<Header> getHeader() {
        return headerService.getHeader()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
} 