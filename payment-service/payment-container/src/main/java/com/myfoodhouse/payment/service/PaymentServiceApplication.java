package com.myfoodhouse.payment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages="com.myfoodhouse.payment.service.dataacess")
@EntityScan(basePackages = "com.food.ordering.system.payment.service.dataacess")
@SpringBootApplication(scanBasePackages="com.myfoodhouse.payment.service")
public class PaymentServiceApplication {
    public static void main(String[] args) { 
        SpringApplication.run(PaymentServiceApplication.class, args); 
    }    
}
