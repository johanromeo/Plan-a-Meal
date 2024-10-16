package com.jromeo.backend.grocerylist.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailDeliveryService {

    private final JavaMailSender javaMailSender;

    public EmailDeliveryService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendGroceryShoppingListToMail(String provisionsToBuy, String[] emailTo) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailSettings = new MimeMessageHelper(message, true);

        mailSettings.setTo(emailTo);
        mailSettings.setSubject("Grocery Shopping List");
        mailSettings.setText(provisionsToBuy);

        javaMailSender.send(message);
    }
}
