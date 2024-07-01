package com.myfoodhouse.order.domain.core;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.entity.OrderItem;
import com.myfoodhouse.order.domain.core.entity.Product;
import com.myfoodhouse.order.domain.core.entity.Restaurant;
import com.myfoodhouse.order.domain.core.event.OrderCancelledEvent;
import com.myfoodhouse.order.domain.core.event.OrderCreatedEvent;
import com.myfoodhouse.order.domain.core.event.OrderPaidEvent;
import com.myfoodhouse.order.domain.core.exception.OrderDomainException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceImpl implements IOrderService {
    
    private static final String UTC = "UTC"; 

    @Override
    public OrderCreatedEvent validateAndInititateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant); 
        setOrderProductInformation(order, restaurant); 
        order.validateOrder(); 
        order.initializeOrder(); 
        log.info("Order with id : {} is initiated", order.getId().getValue()); 
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC))); 
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        Map<UUID, Product> restProds = restaurant.getProducts().stream().collect(Collectors.toMap(p -> p.getId().getValue(), Function.identity())); 
        order.getItems().stream().map(OrderItem::getProduct).forEach(
            p -> { 
                if(restProds.containsKey(p.getId().getValue()))
                    p.updateWithConfirmedNameAndPrice(restProds.get(p.getId().getValue()).getName(), restProds.get(p.getId().getValue()).getPrice()); 
            });
    }

    private void validateRestaurant(Restaurant restaurant) {
        if(!restaurant.isActive())
            throw new OrderDomainException("Restaurant " + restaurant.getId().getValue() + " not active"); 
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id : {} is paid", order.getId().getValue()); 
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC))); 
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id : {} is approved", order.getId().getValue());
        // return new OrderP
    }

    @Override
    public OrderCancelledEvent cancelOrderEvent(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id: {}", order.getId().getValue()); 
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC))); 
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id : {} is cancelled", order.getId().getValue()); 
    }
    
}
