package com.auto.security.manager;


import com.auto.security.constant.UserRequestDto;
import com.auto.security.constant.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    // public ResponseEntity<UserRequestDto> register(UserRequestDto userDto) ;
    public ResponseEntity<UserResponseDto> login(UserRequestDto userDto) ;
    public boolean validToken(String token) ;
    public void authenticate(String userName) ;
    public void sendNotification(String url) ;
}
