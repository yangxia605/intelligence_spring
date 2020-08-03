package com.intelligent;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.intelligent.dao", "com.intelligent.controller","com.intelligent.config", "com.intelligent.service.impl"})
@MapperScan("com.intelligent.controller")
@EnableJpaRepositories
@EnableSwagger2 //必须存在
@ComponentScan(basePackages = {"com.intelligent.dao", "com.intelligent.controller", "com.intelligent.config",
        "com.intelligent.service", "com.intelligent.interceptor"})
public class IntelligentApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligentApplication.class, args);
    }

}
