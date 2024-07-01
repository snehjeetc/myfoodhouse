package com.myfoodhouse.order.domain.core.event;

import java.time.ZonedDateTime;

import com.myfoodhouse.order.domain.core.entity.Order;

public class OrderPaidEvent extends OrderEvent {

    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }

}
