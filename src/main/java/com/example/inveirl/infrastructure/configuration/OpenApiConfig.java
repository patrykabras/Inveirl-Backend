package com.example.inveirl.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(servers = { @Server(description = "Local ENV", url = "http://localhost:8080") },
                   security = { @SecurityRequirement(name = "bearerAuth") },
                   info = @Info(title = "Inveirl", version = "v1"))
@SecurityScheme(name = "Bearer Authentication",
                description = "JWT auth description",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer")
@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi userGroup() {
        return GroupedOpenApi.builder()
                             .group("user")
                             .addOperationCustomizer((operation, handlerMethod) -> {
                                 operation.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList(
                                         "basicScheme"));
                                 return operation;
                             })
                             .packagesToScan("com.example.inveirl.features")
                             .build();
    }

    @Bean
    public GroupedOpenApi adminGroup() {
        return GroupedOpenApi.builder()
                             .group("admin")
                             .addOperationCustomizer((operation, handlerMethod) -> {
                                 operation.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList(
                                         "basicScheme"));
                                 return operation;
                             })
                             .packagesToScan("com.example.inveirl.admin")
                             .build();
    }

    @Bean
    public GroupedOpenApi authGroup() {
        return GroupedOpenApi.builder()
                             .group("auth")
                             .addOperationCustomizer((operation, handlerMethod) -> {
                                 operation.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList(
                                         "basicScheme"));
                                 return operation;
                             })
                             .packagesToScan("com.example.inveirl.auth")
                             .build();
    }
}