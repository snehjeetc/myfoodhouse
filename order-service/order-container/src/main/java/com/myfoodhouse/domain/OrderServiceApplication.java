package com.myfoodhouse.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.myfoodhouse.service.dataacess")
@EntityScan(basePackages="com.myfoodhouse.service.dataacess")
@SpringBootApplication(scanBasePackages="com.myfoodhouse")
public class OrderServiceApplication {
    public static void main(String[] args) { 
        SpringApplication.run(OrderServiceApplication.class, args); 
    }
}
