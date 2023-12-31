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
public class RoleRepositoryTest {

    private RoleRepository roleRepository ;

    @Autowired
    RoleRepositoryTest(RoleRepository roleRepository){
        this.roleRepository = roleRepository ;

        Role role1 = new Role(1l , "ADMIN" ) ;
        Role role2 = new Role(2l , "USER" ) ;

        roleRepository.saveAll(List.of(role1,role2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<Role> brandList = roleRepository.findAll() ;
        Assertions.assertEquals(brandList.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        Role brandOptional = roleRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(brandOptional.getId() ,  1);
    }

}

