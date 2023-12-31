package com.auto.entity.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    private UserRepository userRepository ;

    @Autowired
    UserRepositoryTest(UserRepository userRepository){
        this.userRepository = userRepository ;

        User user1 = new User(1l,"med@gmail.com" , "123" ,new Role(1l,"USER")) ;
        User user2 = new User(2l,"zakaria@gmail.com" , "123" ,new Role(2l,"ADMIN")) ;

        userRepository.saveAll(List.of(user1,user2)) ;

    }


    @Test
    public void getListOfBrand(){
        List<User> userList = userRepository.findAll() ;
        Assertions.assertEquals(userList.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        User user = userRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(user.getId() ,  1);
    }

}
