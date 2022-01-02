package com.jojoldu.book.spring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.*;

//@EnableJpaAuditing  //JPA Auditing 활성화
@SpringBootApplication
//@ComponentScan(basePackages = {"com.jojoldu.book.spring.web"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
