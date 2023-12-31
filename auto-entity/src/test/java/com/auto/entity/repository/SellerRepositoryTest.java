package com.auto.entity.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SellerRepositoryTest {
    private SellerRepository sellerRepository ;

    @Autowired
    public SellerRepositoryTest(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;

        Seller seller  = Seller.builder()
                .name("Mohamed")
                .date(new Date())
                .stars(2)
                .ville("Agadir")
                .phone("0612859252")
                .position_x(23.2)
                .position_y(32.3)
                .province("Sous-Massa").build() ;

        sellerRepository.save(seller) ;
    }


    @Test
    public void findAll(){
        List<Seller> sellerList = sellerRepository.findAll() ;
        Assertions.assertNotNull(sellerList);
        Assertions.assertEquals(1 , sellerList.size());
    }


    @Test
    public void  getById(){
        Optional<Seller> seller = sellerRepository.findById(1L) ;

        Assertions.assertNotNull(seller);
        Assertions.assertNotNull(seller.get().getId());
        Assertions.assertEquals(1 , seller.get().getId());
    }


}
