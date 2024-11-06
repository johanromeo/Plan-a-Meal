package com.jromeo.backend.openai.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The type Rest template configuration.
 *
 * @author Johan Romeo
 */
@Configuration
public class RestTemplateConfiguration {

    /**
     * Rest template rest template.
     *
     * @return the rest template
     * @author Johan Romeo
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
