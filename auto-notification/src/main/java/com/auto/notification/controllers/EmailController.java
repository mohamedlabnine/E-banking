package com.auto.notification.controllers;

import com.auto.notification.config.Constant;
import com.auto.notification.manager.EmailDto;
import com.auto.notification.manager.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/api/")
@AllArgsConstructor
public class EmailController {

    private EmailService emailServiceBased ;


    @GetMapping("/register/{email}")
    public void loginNotification(@PathVariable String email){
        EmailDto emailDto = new EmailDto(Constant.login, email, Constant.templateLogin);
        emailServiceBased.sendEmail(emailDto);
    }

    @GetMapping("/resetPassword/{email}")
    public void resetPasswordNotification(@PathVariable String email){
        EmailDto emailDto = new EmailDto(Constant.resetPassword, email , Constant.templateResetPassword);
        emailServiceBased.sendEmail(emailDto);
    }

}
