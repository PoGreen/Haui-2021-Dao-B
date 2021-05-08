package com.haui.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private static final String SUBJECT = "From: DaoGreenT - Bất động sản của bạn đã được quan tâm";
    private static final String EMAIL = "trandaogrey@gmail.com";

    @Autowired
    private JavaMailSender mailSender;

    public void send(String emailAddress, String emailCustomer) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(EMAIL);
        helper.setSubject(SUBJECT);
        helper.setText("https://mail.google.com/mail/u/0/?fs=1&tf=cm&to=" + emailCustomer);

        helper.setTo(emailAddress);
        mailSender.send(message);
    }

}
