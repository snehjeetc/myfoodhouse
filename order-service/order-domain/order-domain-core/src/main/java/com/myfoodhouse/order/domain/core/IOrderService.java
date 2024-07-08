package com.myfoodhouse.order.domain.core;

import java.util.List;

import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.entity.Restaurant;
import com.myfoodhouse.order.domain.core.event.OrderCancelledEvent;
import com.myfoodhouse.order.domain.core.event.OrderCreatedEvent;
import com.myfoodhouse.order.domain.core.event.OrderPaidEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface IOrderService {
    OrderCreatedEvent validateAndInititateOrder(Order order, Restaurant restaurant, DomainEventPublisher<OrderCreatedEvent> domainEventPublisher); 
    OrderPaidEvent payOrder(Order order, DomainEventPublisher<OrderPaidEvent> orderPaidEventPublisher); 
    void approveOrder(Order order); 
    OrderCancelledEvent cancelOrderEvent(Order order, List<String> failureMessages, DomainEventPublisher<OrderCancelledEvent> orderCancelledEventPublisher); 
    void cancelOrder(Order order, List<String> failureMessages);    
}
