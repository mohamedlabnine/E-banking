package com.auto.security.manager.serviceImpl;


import com.auto.entity.Entities.User;
import com.auto.entity.Repositorys.UserRepository;
import com.auto.security.constant.UserRequestDto;
import com.auto.security.constant.UserResponseDto;
import com.auto.security.manager.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private RestTemplate restTemplate ;

    private JwtUtil jwtUtil ;

    public UserService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    public ResponseEntity<UserResponseDto> login(UserRequestDto userDto){
        log.info("log in user {}" , userDto.getEmail());
        ResponseEntity<UserResponseDto> userDtoSending ;
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail()) ;
        User user ;
        boolean correct_password ;
        if(!userOptional.isEmpty()){
            user = userOptional.get() ;
            correct_password = passwordEncoder.matches(userDto.getPassword() , user.getPassword()) ;
            if(correct_password) {
                this.authenticate(user.getEmail()) ;
                userDtoSending = ResponseEntity.ok(new UserResponseDto(user.getId(), user.getRole(), jwtUtil.generateTokenJwt(user.getId(), user.getRole(), user.getEmail())));
            }
            else
                userDtoSending = new ResponseEntity(null , HttpStatus.NOT_FOUND) ;
        }
        else {
            userDtoSending = new ResponseEntity(null , HttpStatus.NOT_FOUND) ;
        }
        return userDtoSending ;
    }

    public boolean validToken(String token){
        log.info("check token is valid");
        String userName = jwtUtil.extractUsername(token) ;
        boolean isTokenValid = jwtUtil.validateToken(token) ;
        if(isTokenValid) this.authenticate(userName);
        return isTokenValid ;
    }

    public void authenticate(String userName){

        log.info("authenticate user {} " , userName);

        User user = userRepository.findByEmail(userName).get() ;
        UserDetails userDetails = new CustomUserDetails(user) ;
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void sendNotification(String url){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        log.info("preparation request for email notification");

        restTemplate
                .exchange(url , HttpMethod.GET, entity, Void.class).getBody() ;

        log.info("completed");
    }

}
