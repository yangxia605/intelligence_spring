package com.intelligent;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.intelligent.dao", "com.intelligent.controller","com.intelligent.config", "com.intelligent.service.impl"})
@MapperScan("com.intelligent.controller")
public class IntelligentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelligentApplication.class, args);
	}

}
