package com.surnoi.employeeData.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailService {
    public static final String MAIL = "sirajp71@gmail.com";
    private final MailSenderFactory mailSenderFactory;

    public MailService(MailSenderFactory mailSenderFactory) {
        this.mailSenderFactory = mailSenderFactory;
    }


    public void sendMail(String to, String subject, String text, List<String> toAll){
        JavaMailSender mailSender = this.mailSenderFactory.getSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setCc(toAll.toArray(new String[0]));
        mailMessage.setText(text);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(MAIL);
        mailSender.send(mailMessage);
    }

    public void sendForgotMail(String password,String to, String subject) throws MessagingException {
        JavaMailSender mailSender = this.mailSenderFactory.getSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
        String htmlMessage = "<p><b>Your Login details for Student Management System</b><br><b>Email: </b> " + to + " <br><b>Password: </b> " + password + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>";
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setFrom(MAIL);
        message.setContent(htmlMessage,"text/html");
        mailSender.send(message);
    }
}