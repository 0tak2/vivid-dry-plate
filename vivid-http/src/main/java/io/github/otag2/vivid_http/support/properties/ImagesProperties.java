package io.github.otag2.vivid_http.support.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "image")
@Getter
@Setter
@ToString
public class ImagesProperties {
    private String path;
    private String originalPath;
    private String resultPath;
    private String thumbnailPath;
}
