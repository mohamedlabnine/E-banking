package com.transfertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.auto.entity.Repositorys")
@EntityScan(basePackages ="com.auto.entity.Entities")
public class TransfertServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransfertServiceApplication.class, args);
    }
}
