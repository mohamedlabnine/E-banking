package com.auto.entity.repository;


import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class brandRepositoryTest {

    private BrandRepository brandRepository ;

    @Autowired
    brandRepositoryTest(BrandRepository brandRepository){
        this.brandRepository = brandRepository ;

        Brand brand1 = new Brand(1l , "Brand1" , new ArrayList<>()) ;
        Brand brand2 = new Brand(2l , "Brand2" , new ArrayList<>()) ;
        brandRepository.saveAll(List.of(brand1,brand2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<Brand> brandList = brandRepository.findAll() ;
        Assertions.assertEquals(brandList.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        Brand brandOptional = brandRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(brandOptional.getId() ,  1);
    }

}
