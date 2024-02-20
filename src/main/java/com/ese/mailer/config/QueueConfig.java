package com.ese.mailer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "queue")
public class QueueConfig {

    @Value("url")
    private String url;

    @Value("name")
    private String name;
}
