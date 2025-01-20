package com.example.inveirl.features.dictionary;

import com.example.inveirl.infrastructure.enumeration.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Tag(name = "Dictionary")
class DictionaryEndpoint {

    private final DictionaryService service;

    @Operation(summary = "Get dictionary", description = "GET method that returns dictionary")
    @GetMapping("/dictionary")
    @RolesAllowed({ Role.ADMIN, Role.USER })
    public Map<String, List<String>> getDictionary() {
        return service.getDictionary();
    }
}