package com.rapa.diamonds.controller;


import com.rapa.diamonds.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserDetailsController {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsController.class);



    @PostMapping("/details")
    public ResponseEntity<com.rapa.diamonds.model.Message> post(User user) throws MessagingException {

        String request = "New Incoming request from : " + user.getFirstName() + " " + user.getLastName() +
        "\nWith message : \n" + user.getMessage().trim();

        // send email

        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    send(request);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });



        return new ResponseEntity<com.rapa.diamonds.model.Message>(new com.rapa.diamonds.model.Message("success"), HttpStatus.OK);
    }

//    public static void main(String[] args) throws MessagingException {
//        send();
//    }

    static private void send(String requestMessage) throws MessagingException {
        // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String from = "yrapa12@gmail.com";
        String to = "rapaport.z@gmail.com";
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);


        Session session = Session.getDefaultInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "Imsosexy120");
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        msg.setSubject("Purchase request");
        msg.setSentDate(new Date());
        // set plain text message
        msg.setText(requestMessage);

        Transport.send(msg);
        System.out.println("Send Success!!!");


        System.out.println("Done");

    }
}


