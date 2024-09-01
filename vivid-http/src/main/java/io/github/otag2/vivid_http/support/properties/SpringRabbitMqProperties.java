package io.github.otag2.vivid_http.support.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class SpringRabbitMqProperties {
    private String host;
    private int port;
    private String username;
    private String password;
}
