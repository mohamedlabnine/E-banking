package com.auto.entity;


import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest()
@EnableJpaRepositories(basePackages = "com.auto.entity.repository")
@EntityScan(basePackages = "com.auto.entity.entity")
@SpringBootConfiguration
class AutoBackDataAccessApplicationTests {
	@Test
		void contextLoads() {
	}
}


