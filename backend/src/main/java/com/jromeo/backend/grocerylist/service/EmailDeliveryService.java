package com.jromeo.backend.grocerylist.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * The type Email delivery service.
 *
 * @author Johan Romeo
 */
@Component
@Slf4j
public class EmailDeliveryService {

    private final JavaMailSender javaMailSender;

    /**
     * Instantiates a new Email delivery service.
     *
     * @param javaMailSender the java mail sender
     * @author Johan Romeo
     */
    public EmailDeliveryService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Send grocery shopping list to mail.
     *
     * @param provisionsToBuy the provisions to buy
     * @param emailTo         the email to
     * @throws MessagingException the messaging exception
     * @author Johan Romeo
     */
    public void sendGroceryShoppingListToMail(String provisionsToBuy, String[] emailTo) throws MessagingException {
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
