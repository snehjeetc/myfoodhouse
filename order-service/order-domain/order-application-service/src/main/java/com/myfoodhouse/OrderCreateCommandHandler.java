package com.myfoodhouse;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myfoodhouse.dto.create.CreateOrderCommand;
import com.myfoodhouse.dto.create.CreateOrderResponse;
import com.myfoodhouse.mapper.OrderDataMapper;
import com.myfoodhouse.order.domain.core.IOrderService;
import com.myfoodhouse.order.domain.core.entity.Customer;
import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.entity.Restaurant;
import com.myfoodhouse.order.domain.core.event.OrderCreatedEvent;
import com.myfoodhouse.order.domain.core.exception.OrderDomainException;
import com.myfoodhouse.ports.output.repository.CustomerRepository;
import com.myfoodhouse.ports.output.repository.OrderRepository;
import com.myfoodhouse.ports.output.repository.RestaurantRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCreateCommandHandler {
   
    private final OrderDataMapper orderDataMapper;      
    private final IOrderService orderDomainService; 
    private final OrderRepository orderRepository; 
    private final CustomerRepository customerRepository; 
    private final RestaurantRepository restaurantRepository; 
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher; 
    
    //This will not be there in publish-event-1 where OrderCreateHelper is used.

    public OrderCreateCommandHandler(OrderDataMapper orderDataMapper, IOrderService orderDomainService,
            OrderRepository orderRepository, CustomerRepository customerRepository,
            RestaurantRepository restaurantRepository,
            ApplicationDomainEventPublisher applicationDomainEventPublisher) {
        this.orderDataMapper = orderDataMapper;
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.applicationDomainEventPublisher = applicationDomainEventPublisher;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand){ 
        checkCustomer(createOrderCommand.getCustomerId()); 
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand); 
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInititateOrder(order, restaurant);
        Order orderResult = saveOrder(order); 
        log.info("Order is created with id : {}", orderResult.getId().getValue()); 
        applicationDomainEventPublisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder()); 
    }
 
    
    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.crateOrderCommandToRestaurant(createOrderCommand); 
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant); 
        if(optionalRestaurant.isEmpty()){ 
            log.warn("Restaurant with id : {} doesn't exist", restaurant.getId().getValue()); 
            throw new OrderDomainException("Restaurant id : " + restaurant.getId().getValue() + "doesn't exist"); 
        }
        return optionalRestaurant.get(); 
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId); 
        if(customer.isEmpty()) { 
            log.warn("Could not find customer with Customer id : {}", customerId); 
            throw new OrderDomainException("Could not find customer with Customer id :" + customerId); 
        } 
    }

    private Order saveOrder(Order order) { 
        Order orderResult = orderRepository.save(order); 
        if(orderResult == null) 
            throw new OrderDomainException("Could not save order"); 
        log.info("Order is saved with id : {}", orderResult.getId().getValue()); 
        return orderResult; 
    }

    
}
