package com.shemeel.portfolio.controller;

import com.shemeel.portfolio.model.ApiKey;
import com.shemeel.portfolio.service.ApiKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/api-keys")
@RequiredArgsConstructor
@Tag(name = "API Key Management", description = "Endpoints for managing API keys")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping
    @Operation(summary = "Create a new API key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API key created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ApiKey> createApiKey(@RequestBody ApiKey apiKey) {
        return ResponseEntity.ok(apiKeyService.createApiKey(apiKey));
    }

    @GetMapping
    @Operation(summary = "Get all API keys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all API keys")
    })
    public ResponseEntity<List<ApiKey>> getAllApiKeys() {
        return ResponseEntity.ok(apiKeyService.getAllApiKeys());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get API key by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved API key"),
            @ApiResponse(responseCode = "404", description = "API key not found")
    })
    public ResponseEntity<ApiKey> getApiKeyById(@PathVariable String id) {
        return apiKeyService.getApiKeyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an API key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "API key updated successfully"),
            @ApiResponse(responseCode = "404", description = "API key not found")
    })
    public ResponseEntity<ApiKey> updateApiKey(@PathVariable String id, @RequestBody ApiKey apiKey) {
        apiKey.setId(id);
        return ResponseEntity.ok(apiKeyService.updateApiKey(apiKey));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an API key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "API key deleted successfully"),
            @ApiResponse(responseCode = "404", description = "API key not found")
    })
    public ResponseEntity<Void> deleteApiKey(@PathVariable String id) {
        apiKeyService.deleteApiKey(id);
        return ResponseEntity.noContent().build();
    }
} 