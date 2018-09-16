package com.pet.planet.domain.service.mail;

/**
 * Created by gabrieldyck on 12/10/16.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailSender {


    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    private static final Logger LOGGER= LoggerFactory.getLogger(MailSender.class);
    private static void generateServerProperties(){
        // Step1
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        LOGGER.info("Mail Server Properties have been setup successfully..");

        generateMailMessage = new MimeMessage(getMailSession);
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);

        // Step2
    }


    public static void generateAndSendEmail(String subject, String emailBody,String recipient) {
        try {
            generateServerProperties();

            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            generateMailMessage.setText(emailBody);
            generateMailMessage.setSubject(subject);
            LOGGER.info("Mail Session has been created successfully..");

            // Step3
            LOGGER.info("Enviando Email ==========>>>>");
            Transport transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "aaa", "aaa");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            LOGGER.info("Mail enviado <<<<==========");
            transport.close();

        }catch (Exception e){
        e.printStackTrace();
        }
    }









}


