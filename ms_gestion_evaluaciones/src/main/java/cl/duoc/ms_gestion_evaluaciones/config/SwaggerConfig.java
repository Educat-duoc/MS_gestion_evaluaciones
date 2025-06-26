package cl.duoc.ms_gestion_evaluaciones.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {
@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Gestión de Evaluaciones")
                .version("1.0")
                .description("Documentación de la API para el microservicio de gestión de evaluaciones"));
    }
}
