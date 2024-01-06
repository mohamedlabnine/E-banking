package com.auto.notification.controllers;

import com.auto.notification.manager.EmailDto;
import com.auto.notification.manager.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/notification")
@AllArgsConstructor
public class EmailController {

    private EmailService emailServiceBased ;


    @PostMapping
    public void loginNotification(@RequestBody EmailDto emailDto){
        emailServiceBased.sendEmail(emailDto);
    }


}
