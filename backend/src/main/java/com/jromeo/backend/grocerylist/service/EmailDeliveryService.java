package com.jromeo.backend.grocerylist.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Class implementing {@link JavaMailSender} to send emails.
 *
 * @author Johan Romeo
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class EmailDeliveryService {

    private final JavaMailSender javaMailSender;

    /**
     * Sends an email to the user(s) consisting of all provisions to buy.
     *
     * @param provisionsToBuy the provisions to buy.
     * @param emailTo the user(s) email addresses.
     */
    public void sendGroceryShoppingListToMail(String provisionsToBuy, String[] emailTo) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mailSettings = new MimeMessageHelper(message, true);

            mailSettings.setTo(emailTo);
            mailSettings.setSubject("Grocery Shopping List");
            mailSettings.setText(provisionsToBuy);

            javaMailSender.send(message);
        } catch (Exception e) {
            log.warn("Could not send mail: {}.", e.getMessage() + e.getLocalizedMessage());
        }
    }
}
