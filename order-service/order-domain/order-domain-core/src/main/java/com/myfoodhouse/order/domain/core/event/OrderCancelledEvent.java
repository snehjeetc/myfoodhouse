package com.myfoodhouse.order.domain.core.event;

import java.time.ZonedDateTime;

import com.myfoodhouse.order.domain.core.entity.Order;

public class OrderCancelledEvent extends OrderEvent {
    
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
    
}
