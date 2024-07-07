package com.myfoodhouse.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myfoodhouse.order.domain.core.IOrderService;
import com.myfoodhouse.order.domain.core.OrderServiceImpl;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public IOrderService orderDomainService() { 
        return new OrderServiceImpl(); 
    }

    

}
