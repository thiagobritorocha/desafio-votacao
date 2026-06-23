package br.com.dbserver.desafiovotacao.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "${spring.application.name}"))
public class OpenAPIConfiguration {}
