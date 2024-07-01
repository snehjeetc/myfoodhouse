package com.myfoodhouse.ports.input.service;

import com.myfoodhouse.dto.create.CreateOrderCommand;
import com.myfoodhouse.dto.create.CreateOrderResponse;
import com.myfoodhouse.dto.track.TrackOrderQuery;
import com.myfoodhouse.dto.track.TrackOrderResponse;

import jakarta.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand); 
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery); 
}
