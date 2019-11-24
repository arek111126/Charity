package pl.coderslab.charity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {


    @Qualifier("getJavaMailSender")

    @Autowired
    public JavaMailSender emailSender;
 
    public void sendSimpleMessage(
      String to, String subject, String text) throws MessagingException {

//        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage msg = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        emailSender.send(msg);

    }
}