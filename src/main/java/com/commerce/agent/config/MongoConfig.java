package com.commerce.agent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Data
@Configuration
@Profile("application-local.properties")
@ConfigurationProperties(prefix = "spring.mongo")
public class MongoConfig {
    List<String> database;
    String host;
    String port;
}
