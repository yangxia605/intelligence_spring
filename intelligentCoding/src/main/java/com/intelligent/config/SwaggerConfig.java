package com.intelligent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;

@SwingContainer
@Configuration //必须存在
public class SwaggerConfig {
    @Bean
    public Docket customDocket(){
        return  new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("杨霞","http://www.intelligence.com","yangxia605@gmail.com");
        return  new ApiInfoBuilder()
                .title("智慧编程项目接口")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
