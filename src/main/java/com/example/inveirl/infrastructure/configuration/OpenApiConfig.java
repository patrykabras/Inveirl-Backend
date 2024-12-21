package com.example.inveirl.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = { @Server(description = "Local ENV", url = "http://localhost:8080") },
                   security = { @SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(name = "Bearer Authentication",
                description = "JWT auth description",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer")
public class OpenApiConfig {
}