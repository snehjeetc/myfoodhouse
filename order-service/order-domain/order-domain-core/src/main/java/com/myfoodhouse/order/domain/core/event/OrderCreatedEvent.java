package com.myfoodhouse.order.domain.core.event;

import java.time.ZonedDateTime;

import com.myfoodhouse.order.domain.core.entity.Order;

public class OrderCreatedEvent extends OrderEvent {
    
    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
    
}
