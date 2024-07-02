package com.myfoodhouse.order.service.domain;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.myfoodhouse.order.domain.core.IOrderService;
import com.myfoodhouse.order.domain.core.OrderServiceImpl;
import com.myfoodhouse.order.domain.core.entity.Restaurant;
import com.myfoodhouse.ports.output.message.publisher.payment.OrderCancelledPaymetRequestMessagePublisher;
import com.myfoodhouse.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.myfoodhouse.ports.output.message.publisher.restaurant.OrderPaidRestaurantRequestPublisher;
import com.myfoodhouse.ports.output.repository.CustomerRepository;
import com.myfoodhouse.ports.output.repository.OrderRepository;
import com.myfoodhouse.ports.output.repository.RestaurantRepository;


@SpringBootApplication(scanBasePackages = "com.myfoodhouse")
public class OrderTestConfigurationClass {

    /*
     * Mocking the adapters below 
     */
    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() { 
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class); 
    }

    @Bean
    public OrderCancelledPaymetRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() { 
        return Mockito.mock(OrderCancelledPaymetRequestMessagePublisher.class); 
    }

    @Bean
    public OrderPaidRestaurantRequestPublisher orderPaidRestaurantRequestPublisher() { 
        return Mockito.mock(OrderPaidRestaurantRequestPublisher.class); 
    }

    @Bean
    public OrderRepository orderRepository() { 
        return Mockito.mock(OrderRepository.class); 
    }

    @Bean
    public CustomerRepository customerRepository() { 
        return Mockito.mock(CustomerRepository.class); 
    }

    @Bean 
    public RestaurantRepository restaurantRepository() { 
        return Mockito.mock(RestaurantRepository.class); 
    }

    @Bean
    public IOrderService orderDomainService() { 
        return new OrderServiceImpl(); 
    }

}
