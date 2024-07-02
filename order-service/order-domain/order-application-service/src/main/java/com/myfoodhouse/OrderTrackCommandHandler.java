package com.myfoodhouse;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myfoodhouse.dto.track.TrackOrderQuery;
import com.myfoodhouse.dto.track.TrackOrderResponse;
import com.myfoodhouse.mapper.OrderDataMapper;
import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.order.domain.core.exception.OrderNotFoundException;
import com.myfoodhouse.order.domain.core.valueobject.TrackingId;
import com.myfoodhouse.ports.output.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    
    private final OrderDataMapper orderDataMapper; 
    private final OrderRepository orderRepository; 

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly= true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> optionalOrder = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId())); 
        if(optionalOrder.isEmpty()){
            log.warn("Could not find order with tracking id : {}", trackOrderQuery.getOrderTrackingId()); 
            throw new OrderNotFoundException("Order id : " + trackOrderQuery.getOrderTrackingId() + " not found"); 
        }
        return orderDataMapper.orderToTrackOrderResponse(optionalOrder.get()) ; 
    }
}
