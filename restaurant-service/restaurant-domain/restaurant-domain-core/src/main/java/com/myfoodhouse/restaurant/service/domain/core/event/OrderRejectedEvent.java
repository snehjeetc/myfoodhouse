package com.myfoodhouse.restaurant.service.domain.core.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.myfoodhouse.restaurant.service.domain.core.entity.OrderApproval;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantId;

public class OrderRejectedEvent extends OrderApprovalEvent {
    
    private final DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher;

    public OrderRejectedEvent(OrderApproval orderApproval,
                              RestaurantId restaurantId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt,
                              DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher) {
        super(orderApproval, restaurantId, failureMessages, createdAt);
        this.orderRejectedEventDomainEventPublisher = orderRejectedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderRejectedEventDomainEventPublisher.publish(this);
    }
}
