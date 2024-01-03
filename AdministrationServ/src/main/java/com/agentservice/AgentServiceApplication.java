package com.agentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.auto.entity.Repositorys")
@EntityScan(basePackages ="com.auto.entity.Entities")
public class AgentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgentServiceApplication.class, args);
    }
}
