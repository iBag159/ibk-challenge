package ibk.ibag.challenge.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean //startup de la app
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo("Interbank Challenge", "Challenge for backend trainee :c", "1.0", "http://ibk.challenge.com",
                        new Contact("Gaby A. Carbajal", "tawadevs.com", "gabyalvarez159@gmail.com"),
                        "Exclusive API for a hard challenge", "http://anfix.com", Collections.emptyList()));
    }
}
