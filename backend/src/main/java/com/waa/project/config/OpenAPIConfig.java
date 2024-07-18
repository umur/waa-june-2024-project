package com.waa.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components().addSecuritySchemes("bearer-jwt", new SecurityScheme().type(
                                    SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                            .info(new Info().title("API Documentation").version("1.0"))
                            .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"));
    }


    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> openApi.getPaths()
                                 .values()
                                 .forEach(pathItem -> pathItem.readOperations()
                                                              .forEach(operation -> operation.addSecurityItem(
                                                                      new SecurityRequirement().addList(
                                                                              "bearer-jwt"))));
    }
}