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
import com.myfoodhouse.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.myfoodhouse.ports.output.repository.CustomerRepository;
import com.myfoodhouse.ports.output.repository.OrderRepository;
import com.myfoodhouse.ports.output.repository.RestaurantRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCreateCommandHandler {
   
    private final OrderCreateHelper orderCreateHelper; 
    private final OrderDataMapper orderDataMapper; 
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher; 

    public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper, OrderDataMapper orderDataMapper,
            OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreateHelper = orderCreateHelper;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand){ 
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand); 
        log.info("Order is created with id : {}", orderCreatedEvent.getOrder().getId().getValue()); 
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);        
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder()); 
    }

    
}
