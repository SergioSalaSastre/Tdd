package com.sergioTdd.tdd_hexagonal_backend.infraestructure.API.Config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TDD Hexagonal API")
                        .version("1.0")
                        .description("Documentación generada con Swagger"));
    }
}

