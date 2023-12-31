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
public class OptionRepositoryTest {

    private OptionRepository optionRepository ;

    @Autowired
    OptionRepositoryTest(OptionRepository optionRepository){
        this.optionRepository = optionRepository ;

        Equipment equipment1 = new Equipment(1l , "equipment1"  ) ;
        Equipment equipment2 = new Equipment(2l , "equipment2") ;
        optionRepository.saveAll(List.of(equipment1,equipment2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<Equipment> brandList = optionRepository.findAll() ;
        Assertions.assertEquals(brandList.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        Equipment brandOptional = optionRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(brandOptional.getId() ,  1);
    }

}

