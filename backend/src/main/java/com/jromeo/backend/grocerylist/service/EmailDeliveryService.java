package com.jromeo.backend.grocerylist.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailDeliveryService {

    private final JavaMailSender javaMailSender;

    private final ResourceLoader resourceLoader;

    public EmailDeliveryService(JavaMailSender javaMailSender, ResourceLoader resourceLoader) {
        this.javaMailSender = javaMailSender;
        this.resourceLoader = resourceLoader;
    }

    public void sendGroceryShoppingListToMail(String provisionsToBuy, String[] emailTo) throws MessagingException {
        final String GROCERY_SHOPPING_LIST = "grocery_shopping_list.txt";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailSettings = new MimeMessageHelper(message, true);

        //Store users associated with email in database
        mailSettings.setTo(emailTo);
        mailSettings.setSubject("Grocery Shopping List");
        mailSettings.setText(provisionsToBuy);

//        Resource groceryShoppingList = getResourceFile(GROCERY_SHOPPING_LIST);
//        mailSettings.addAttachment("HEJ.TXT", groceryShoppingList);

        javaMailSender.send(message);

    }

    private Resource getResourceFile(String filePath) {
        return resourceLoader.getResource("classpath:" + filePath);
    }
}
