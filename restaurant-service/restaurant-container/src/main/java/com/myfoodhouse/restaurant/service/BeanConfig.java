package com.myfoodhouse.restaurant.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myfoodhouse.restaurant.service.domain.core.RestaurantDomainService;
import com.myfoodhouse.restaurant.service.domain.core.RestaurantDomainServiceImpl;

@Configuration
public class BeanConfig {
    
    @Bean
    public RestaurantDomainService restaurantDomainService() {
        return new RestaurantDomainServiceImpl();
    }
}
