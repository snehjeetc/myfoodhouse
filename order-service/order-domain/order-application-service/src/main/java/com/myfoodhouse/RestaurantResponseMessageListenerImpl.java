package com.myfoodhouse;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.myfoodhouse.dto.message.RestaurantApprovalResponse;
import com.myfoodhouse.ports.input.message.listener.restaurantapproval.RestaurantResponseMessageListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Service
public class RestaurantResponseMessageListenerImpl implements RestaurantResponseMessageListener {

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orderApproved'");
    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orderRejected'");
    }
    
}
