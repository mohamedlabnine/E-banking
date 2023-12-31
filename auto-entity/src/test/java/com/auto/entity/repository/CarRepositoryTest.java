package com.auto.entity.repository;

import com.auto.entity.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarRepositoryTest {

    private CarRepository carRepository ;

    @Autowired
    public CarRepositoryTest(CarRepository carRepository) {
        this.carRepository = carRepository;
        Car car = this.buildCar();

        carRepository.save(car) ;
    }

    public Car buildCar(){
        Seller seller  = Seller.builder()
                .name("Mohamed")
                .date(new Date())
                .stars(2)
                .ville("Agadir")
                .phone("0612859252")
                .position_x(23.2)
                .position_y(32.3)
                .province("Sous-Massa").build() ;

        Car car = Car.builder()
                .description("description")
                .volume(33.2).length(33.2).height(23.2).carYear("2333")
                .price(23333).mileage(23333).nbr_month_guarantee(23)
                .box_car(Box_car.AUTOMATIQUE)
                .outside_color(new Color(null , "color1","code1"))
                .inside_color(new Color(null , "color1","code1"))
                .energy(new Energy(null,"energy1")).credit(true).monthly(23)
                .circulation(new Date()).control_tech(true).first_hand(true).nbr_property(3)
                .saddlery("saddlery").nbr_doors(4).nbr_places(5).power_fiscal(23).power_DIN(23)
                .euro_norme("euro").crit_air(2).consomation_mixt("L3 33 33")
                .co2(3).conversion_premium("conversion premium")
                .type(new Type(null , "type1",new ArrayList<>()))
                .model(new Model(null , "model1",new ArrayList<>()))
                .brand(new Brand(null , "brand1",new ArrayList<>()))
                .generalisation(new Generalisation(null , "gene1",new ArrayList<>()))
                .version(new Version(null , "version1"))
                .seller(seller)
                .equipments(List.of(new Equipment())).build() ;

        return car ;
    }

    @Test
    public void findById(){
        Optional<Car> car = carRepository.findById(1L) ;
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.get().getId());
        Assertions.assertEquals(1,car.get().getId());
    }

    @Test
    public void findAll(){
        List<Car> carList = carRepository.findAll() ;
        Assertions.assertEquals(1,carList.size());
    }


}
