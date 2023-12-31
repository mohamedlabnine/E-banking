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
public class EnergyRepositoryTest {

    private EnergyRepository energyRepository ;

    @Autowired
    EnergyRepositoryTest(EnergyRepository energyRepository){
        this.energyRepository = energyRepository ;

        Energy energy1 = new Energy(1l , "energy1"  ) ;
        Energy energy2 = new Energy(2l , "energy2") ;
        energyRepository.saveAll(List.of(energy1,energy2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<Energy> energyList = energyRepository.findAll() ;
        Assertions.assertEquals(energyList.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        Energy energy = energyRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(energy.getId() ,  1);
    }

}
