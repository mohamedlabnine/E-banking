package com.auto.notification.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EmailService {
    @Autowired
    private  JavaMailSender javaMailSender ;

    public void sendEmail(EmailDto emailDto){

        SimpleMailMessage mailMessage = new SimpleMailMessage() ;
        mailMessage.setFrom("mohamedlabnine1@gmail.com");
        mailMessage.setTo(emailDto.getToEmail());
        mailMessage.setSubject(emailDto.getSubject());
        String body = "code : " + emailDto.getCode() + " / ref : " + emailDto.getRef() ;

        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
    }





}
