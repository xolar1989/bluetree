package org.carlos.bluetree.authentication.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("jwt")
public class JwtConfig {
    private String secret;

    private long validity;

    private String prefix;
}
