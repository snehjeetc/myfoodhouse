package com.myfoodhouse.payment.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myfoodhouse.payment.service.domain.core.PaymentDomainService;
import com.myfoodhouse.payment.service.domain.core.PaymentDomainServiceImpl;

@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
