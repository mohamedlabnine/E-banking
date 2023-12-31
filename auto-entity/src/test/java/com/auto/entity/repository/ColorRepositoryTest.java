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
public class ColorRepositoryTest {

    private ColorRepository colorRepository ;

    @Autowired
    ColorRepositoryTest(ColorRepository colorRepository){
        this.colorRepository = colorRepository ;

        Color color1 = new Color(1l , "equipment1" ,"code1" ) ;
        Color color2 = new Color(2l , "equipment2","code2") ;
        colorRepository.saveAll(List.of(color1,color2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<Color> colorList = colorRepository.findAll() ;
        Assertions.assertEquals(colorList.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        Color color = colorRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(color.getId() ,  1);
    }

}


