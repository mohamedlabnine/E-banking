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
public class GeneralizationRepositoryTest {

    private GeneralizationRepository generalizationRepository ;

    @Autowired
    GeneralizationRepositoryTest(GeneralizationRepository generalizationRepository){
        this.generalizationRepository = generalizationRepository ;

        Generalisation generalisation1 = new Generalisation(1l , "gene1" ,new ArrayList<>() ) ;
        Generalisation generalisation2 = new Generalisation(2l , "gene2",new ArrayList<>()) ;

        generalizationRepository.saveAll(List.of(generalisation1,generalisation2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<Generalisation> generalisations = generalizationRepository.findAll() ;
        Assertions.assertEquals(generalisations.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        Generalisation generalisation = generalizationRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(generalisation.getId() ,  1);
    }

}
