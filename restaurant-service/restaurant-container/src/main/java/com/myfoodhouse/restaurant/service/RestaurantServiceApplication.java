package com.myfoodhouse.restaurant.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.myfoodhouse.restaurant.dataaccess")
@EntityScan(basePackages="com.myfoodhouse.restaurant.dataaccess")
@SpringBootApplication(scanBasePackages="com.myfoodhouse.restaurant.service")
public class RestaurantServiceApplication {
    public static void main(String[] args) { 
        SpringApplication.run(RestaurantServiceApplication.class, args); 
    }
}
