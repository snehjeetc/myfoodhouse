package com.myfoodhouse.order.service.application.rest;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfoodhouse.dto.create.CreateOrderCommand;
import com.myfoodhouse.dto.create.CreateOrderResponse;
import com.myfoodhouse.dto.track.TrackOrderQuery;
import com.myfoodhouse.dto.track.TrackOrderResponse;
import com.myfoodhouse.ports.input.service.OrderApplicationService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    //Uses the port defined in OrderApplicationService
    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    /**
     *Both of the below methods are happy flow path 
     To handle the exceptions 
     We are going to use Spring exception handler methods 
     Using controller advice which will help all the exception from all the 
     controller classes to go through those methods.  
     * 
     */


    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) { 
        log.info("Creating order for customer : {} at restaurant : {}", createOrderCommand.getCustomerId(), createOrderCommand.getRestaurantId()); 

        var createOrderResponse = orderApplicationService.createOrder(createOrderCommand); 
        log.info("Order created with tracking id : {}", createOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(createOrderResponse); 
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID pTrackingId) {
        log.info("Received tracking order request for tracking id : {}", pTrackingId); 
        TrackOrderResponse trackOrderResponse = orderApplicationService.trackOrder(
            TrackOrderQuery.builder().orderTrackingId(pTrackingId).build()
        ); 
        log.info("Tracking order response generated : {}", trackOrderResponse.getOrderTrackingId()); 
        return ResponseEntity.ok(trackOrderResponse); 
    }
    




}
