package com.jromeo.backend;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for reading environment variables from a .env-file.
 *
 * @author Johan Romeo
 */
@Configuration
@RequiredArgsConstructor
public class DotenvConfiguration {

    private final ConfigurableEnvironment environment;

    /**
     * Loads the environment variables specified in the .env file in the project's root directory.
     *
     * @author Johan Romeo
     */
    @PostConstruct
    public void loadDotenv() {
        Dotenv dotenv = Dotenv.configure()
                .directory(".")
                .filename(".env")
                .ignoreIfMissing()
                .load();

        Map<String, Object> dotenvMap = new HashMap<>();
        dotenv.entries().forEach(entry -> dotenvMap.put(entry.getKey(), entry.getValue()));

        PropertySource<Map<String, Object>> dotenvPropertySource = new MapPropertySource("dotenv", dotenvMap);
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(dotenvPropertySource);
    }
}
