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
public class ImageBufferRepositoryTest {

    private ImageBufferRepository imageBufferRepository ;

    @Autowired
    ImageBufferRepositoryTest(ImageBufferRepository imageBufferRepository){
        this.imageBufferRepository = imageBufferRepository ;

        ImageBuffer imageBuffer1 = new ImageBuffer(1l,"imageName1" ,"imageBlob1" , null) ;
        ImageBuffer imageBuffer2 = new ImageBuffer(2l,"imageName2" ,"imageBlob2" , null) ;

        imageBufferRepository.saveAll(List.of(imageBuffer1,imageBuffer2)) ;
    }


    @Test
    public void getListOfBrand(){
        List<ImageBuffer> imageBuffers = imageBufferRepository.findAll() ;
        Assertions.assertEquals(imageBuffers.size() ,  2);
    }

    @Test
    public void findBrandById()  {
        ImageBuffer imageBuffer = imageBufferRepository.findById(1l).orElseThrow() ;
        Assertions.assertEquals(imageBuffer.getId() ,  1);
    }

}
