package com.qf.springbootdemo1.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "imgconfig")
public class ImgConfig {

    // @Value("${imgConfig.path}")
    private String path;

    // @Value("${imgConfig.extend}")
    private String extend;

    // @Value("${imgConfig.maxSize}")
    private String maxSize;
}
