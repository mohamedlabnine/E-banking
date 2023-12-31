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
public class VersionRepositoryTest {

    private VersionRepository versionRepository ;

    @Autowired
    VersionRepositoryTest(VersionRepository versionRepository){
        this.versionRepository = versionRepository ;

        Version version1 = new Version(1l , "version1" ) ;
        Version version2 = new Version(2l , "version2") ;
        versionRepository.saveAll(List.of(version1,version2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<Version> brandList = versionRepository.findAll() ;
        Assertions.assertEquals(brandList.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        Version brandOptional = versionRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(brandOptional.getId() ,  1);
    }

}
