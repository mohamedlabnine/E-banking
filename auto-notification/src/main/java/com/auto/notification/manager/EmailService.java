package com.auto.notification.manager;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Slf4j
public class EmailService {
    private JavaMailSender sender;
    private SpringTemplateEngine templateEngine;


    public EmailService(JavaMailSender sender ,  SpringTemplateEngine springTemplateEngine) {
        this.sender = sender;
        this.templateEngine = springTemplateEngine ;
    }


    public void sendEmail(EmailDto emailDto) {

        log.info("build template for email");

        try {

            final Context ctx = new Context();

            ctx.setVariable("email", emailDto.getToEmail());


            final MimeMessage mimeMessage = this.sender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true, "UTF-8");
            message.setSubject(emailDto.getSubject());
            message.setTo(emailDto.getToEmail());

            //message.setFrom(new InternetAddress("LaCentral@gmail.com", "LaCentral"));


            final String htmlContent = this.templateEngine.process( emailDto.getTemplate() , ctx);
            message.setText(htmlContent, true);
            this.sender.send(mimeMessage);


        } catch (Exception e) {

            log.error(e.getMessage());
        }
    }





}
