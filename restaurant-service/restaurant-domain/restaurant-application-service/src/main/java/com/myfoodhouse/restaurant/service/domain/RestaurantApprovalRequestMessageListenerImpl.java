package com.myfoodhouse.restaurant.service.domain;

import com.myfoodhouse.restaurant.service.domain.core.event.OrderApprovalEvent;
import com.myfoodhouse.restaurant.service.domain.dto.RestaurantApprovalRequest;

public class RestaurantApprovalRequestMessageListenerImpl implements com.myfoodhouse.restaurant.service.domain.ports.input.message.listener.RestaurantApprovalRequestMessageListener{
    private final RestaurantApprovalRequestHelper restaurantApprovalRequestHelper;

    public RestaurantApprovalRequestMessageListenerImpl(RestaurantApprovalRequestHelper
                                                                restaurantApprovalRequestHelper) {
        this.restaurantApprovalRequestHelper = restaurantApprovalRequestHelper;
    }

    @Override
    public void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest) {
        OrderApprovalEvent orderApprovalEvent =
                restaurantApprovalRequestHelper.persistOrderApproval(restaurantApprovalRequest);
        orderApprovalEvent.fire();
    }
}
