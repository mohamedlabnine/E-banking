package com.auto.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.auto.entity.Repositorys")
@EntityScan(basePackages ="com.auto.entity.Entities")
public class AutoSecurityApplication {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate() ;
	}
	public static void main(String[] args) {
		SpringApplication.run(AutoSecurityApplication.class, args);
	}
}
