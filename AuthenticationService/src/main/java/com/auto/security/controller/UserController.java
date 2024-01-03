package com.auto.security.controller;

import com.auto.security.constant.ApiUrl;
import com.auto.security.constant.UserRequestDto;
import com.auto.security.constant.UserResponseDto;
import com.auto.security.manager.serviceImpl.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth/")
public class UserController {
    private UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*@PostMapping("register")
    public ResponseEntity<UserRequestDto> register(@RequestBody UserRequestDto userDto){
        return userService.register(userDto) ;
    }*/

    @PostMapping("login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto userDto){
        return userService.login(userDto) ;
    }

    @GetMapping("resetPassword/{email}")
    public void resetPassword(@PathVariable String email){
        userService.sendNotification(ApiUrl.resetPasswordNotificationApi + email);
    }

    @GetMapping("{token}")
    public boolean validToken(@PathVariable String token){
        return userService.validToken(token) ;
    }


}
