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
public class ModelRepositoryTest {

    private ModelRepository modelRepository ;

    @Autowired
    ModelRepositoryTest(ModelRepository modelRepository){
        this.modelRepository = modelRepository ;

        Model version1 = new Model(1l , "model1" , new ArrayList<>() ) ;
        Model version2 = new Model(2l , "model2",new ArrayList<>()) ;
        modelRepository.saveAll(List.of(version1,version2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<Model> brandList = modelRepository.findAll() ;
        Assertions.assertEquals(brandList.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        Model brandOptional = modelRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(brandOptional.getId() ,  1);
    }

}
