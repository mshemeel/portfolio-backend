package com.shemeel.portfolio.controller;

import com.shemeel.portfolio.model.Footer;
import com.shemeel.portfolio.service.FooterService;
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
 * Controller for Footer endpoints
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Footer", description = "Footer API")
public class FooterController {

    private final FooterService footerService;

    /**
     * Get the footer data
     * @return the footer data
     */
    @Operation(
        summary = "Get footer data",
        description = "Retrieves the footer information including copyright and social links"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Footer data found",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Footer.class)
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Footer data not found",
            content = @Content
        )
    })
    @GetMapping("/footer")
    public ResponseEntity<Footer> getFooter() {
        return footerService.getFooter()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
} 