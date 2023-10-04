package com.example.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "Department Service REST APIs",
                description = "Department ServiceREST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Olha",
                        email = "olhachirva@gmail.com",
                        url = "https://github.com/OlgaLopushanska"
                ),
                license = @License(
                        name = "MIT",
                        url = "https://github.com/OlgaLopushanska"
                )
        ), externalDocs = @ExternalDocumentation(
                description = "Department Service Doc",
                url = "https://github.com/OlgaLopushanska"
        )
)
@SpringBootApplication
public class DepartmentServiceApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

}
