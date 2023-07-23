package com.surnoi.employeeData.email;

import org.springframework.mail.javamail.JavaMailSender;

public interface MailSenderFactory {
   // JavaMailSender getSender(String email, String password);
   JavaMailSender getSender();
}
