package ibk.ibag.challenge.configuration;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ToString
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {
    private String name;
    private Integer year;
    private String edition;
    private String[] countries;
}
