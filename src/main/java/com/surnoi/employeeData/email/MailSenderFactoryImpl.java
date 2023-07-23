package com.surnoi.employeeData.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailSenderFactoryImpl implements MailSenderFactory {

    //final String email, final String password
    @Override
    public JavaMailSender getSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(mailProperties());
        //mailSender.setHost(javaMailer.getHost());
        mailSender.setHost("smtp.gmail.com");
/*      mailSender.setUsername(email);
        mailSender.setPassword(password);*/
        mailSender.setUsername("sirajp71@gmail.com");
        mailSender.setPassword("nrygkmaiufozkahj");
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setPort(587);
        return mailSender;
    }

    private Properties mailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.starttls.enable", "false");
        // properties.put(ApplicationConstant.MAIL_SMTPPORT, javaMailer.getPort());
        return properties;
    }
}