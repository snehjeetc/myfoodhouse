package com.myfoodhouse;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.myfoodhouse.dto.create.CreateOrderCommand;
import com.myfoodhouse.dto.create.CreateOrderResponse;
import com.myfoodhouse.dto.track.TrackOrderQuery;
import com.myfoodhouse.dto.track.TrackOrderResponse;
import com.myfoodhouse.ports.input.service.OrderApplicationService;

import lombok.extern.slf4j.Slf4j;


//This will be package private and the implementation will be kept hidden from the 
//outside world only interface will be exposed. 
@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService{

    private final OrderCreateCommandHandler orderCreateCommandHandler; 
    private final OrderTrackCommandHandler trackCommandHandler; 


    //constructor injection
    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
            OrderTrackCommandHandler trackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.trackCommandHandler = trackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand); 
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return trackCommandHandler.trackOrder(trackOrderQuery); 
    }
    
}
