package architecture.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "auth")
@Configuration("authConfiguration")
@Data
public class AuthConfiguration {
    private String issuer;
    private String audience;
    private String secret;
}
