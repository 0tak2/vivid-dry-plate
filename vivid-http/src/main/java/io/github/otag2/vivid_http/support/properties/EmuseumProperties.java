package io.github.otag2.vivid_http.support.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "emuseum")
@Getter
@Setter
public class EmuseumProperties {
    private String serviceKey;
}

