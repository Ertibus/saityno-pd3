package lt.viko.eif.emargevicius.saityno.pd3.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration class
 * Configures SwaggersUI, sets it's info
 *
 * @author Emil
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class SwaggerConfig {
    /**
     * A Spring Bean that sets the header/info section of SwaggerUI
     * @return {@link OpenAPI} object with the header information
     */
    @Bean
    public OpenAPI planetsAPI() {
        return new OpenAPI()
                .info(new Info().title("Planets API")
                        .description("My PlanetsAPI For Web services – Assessment Task 3 JAX-RS Web service")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Emilis Margevičius PI19B").email("emilis.margevicius@stud.viko.lt").url("https://github.com/Ertibus/saityno-pd3"))
                );
    }
}

