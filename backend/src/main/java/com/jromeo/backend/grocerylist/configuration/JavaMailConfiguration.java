package com.jromeo.backend.grocerylist.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configuration class for Gmail settings.
 *
 * @author Johan Romeo
 */
@Configuration
public class JavaMailConfiguration {

    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;

    /**
     * Method for configuring Gmail settings using JavaMailSenderImpl.
     *
     * @return the configured JavaMailSender.
     */
    @Bean
    public JavaMailSender configureGmailProperties() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost("smtp.gmail.com");
        mailSenderImpl.setPort(587);
        mailSenderImpl.setUsername(mailUsername);
        mailSenderImpl.setPassword(mailPassword);

        Properties properties = mailSenderImpl.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        return mailSenderImpl;
    }
}
