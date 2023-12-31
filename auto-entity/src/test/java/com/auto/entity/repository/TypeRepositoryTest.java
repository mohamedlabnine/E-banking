package com.auto.entity.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TypeRepositoryTest {

    private TypeRepository typeRepository ;

    @Autowired
    public TypeRepositoryTest(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;

        Type type1 = new Type(1l,"type1" ,new ArrayList<>()) ;
        Type type2 = new Type(2l,"type2" ,new ArrayList<>()) ;

        typeRepository.saveAll(List.of(type1,type2)) ;

    }

    @Test
    public void getListTypes(){
        List<Type> typeList = typeRepository.findAll() ;

        Assertions.assertEquals(typeList.size() ,2);

    }

    @Test
    public void findTypeById(){
        Type type = typeRepository.findById(1l).orElseThrow() ;

        Assertions.assertEquals(type.getId() , 1l);
    }
}
