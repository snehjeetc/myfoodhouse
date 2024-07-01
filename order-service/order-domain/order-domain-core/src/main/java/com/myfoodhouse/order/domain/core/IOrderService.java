package com.myfoodhouse.order.domain.core;

import java.util.List;

import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.entity.Restaurant;
import com.myfoodhouse.order.domain.core.event.OrderCancelledEvent;
import com.myfoodhouse.order.domain.core.event.OrderCreatedEvent;
import com.myfoodhouse.order.domain.core.event.OrderPaidEvent;

public interface IOrderService {
    OrderCreatedEvent validateAndInititateOrder(Order order, Restaurant restaurant); 
    OrderPaidEvent payOrder(Order order); 
    void approveOrder(Order order); 
    OrderCancelledEvent cancelOrderEvent(Order order, List<String> failureMessages); 
    void cancelOrder(Order order, List<String> failureMessages); 
}
