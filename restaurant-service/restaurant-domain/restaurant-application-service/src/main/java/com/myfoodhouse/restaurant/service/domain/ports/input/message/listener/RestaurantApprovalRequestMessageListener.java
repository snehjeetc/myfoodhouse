package com.myfoodhouse.restaurant.service.domain.ports.input.message.listener;

import com.myfoodhouse.restaurant.service.domain.dto.RestaurantApprovalRequest;

public interface  RestaurantApprovalRequestMessageListener {
    void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
