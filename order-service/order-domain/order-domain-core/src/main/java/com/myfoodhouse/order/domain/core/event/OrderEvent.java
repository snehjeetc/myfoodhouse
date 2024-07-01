package com.myfoodhouse.order.domain.core.event;

import java.time.ZonedDateTime;

import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.sys.domain.events.DomainEvent;

public abstract class OrderEvent implements DomainEvent<Order> {

    private final Order order;
    private final ZonedDateTime createdAt;

    protected OrderEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
