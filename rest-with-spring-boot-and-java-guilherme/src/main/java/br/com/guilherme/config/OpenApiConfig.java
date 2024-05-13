package br.com.guilherme.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info().title("Aplication RestFul with java").version("v1").description("Projeto criado apenas para fins de estudo").termsOfService("Fins de estudo").license(new License().name("Apache 2.0").url("")));

    }
}
