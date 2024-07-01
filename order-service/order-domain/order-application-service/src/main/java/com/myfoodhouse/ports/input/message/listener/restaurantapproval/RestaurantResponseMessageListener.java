package com.myfoodhouse.ports.input.message.listener.restaurantapproval;

import com.myfoodhouse.dto.message.RestaurantApprovalResponse;
import com.myfoodhouse.order.domain.core.entity.Restaurant;

public interface  RestaurantResponseMessageListener {
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse); 
    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse); 
}
