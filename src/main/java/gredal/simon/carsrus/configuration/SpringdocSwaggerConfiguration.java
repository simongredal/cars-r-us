package gredal.simon.carsrus.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!production")
public class SpringdocSwaggerConfiguration {
    @Bean
    public OpenAPI api() {
        return new OpenAPI();
    }
}
