package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cyan
 * @version 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class PathYml {
    private String path;
}
