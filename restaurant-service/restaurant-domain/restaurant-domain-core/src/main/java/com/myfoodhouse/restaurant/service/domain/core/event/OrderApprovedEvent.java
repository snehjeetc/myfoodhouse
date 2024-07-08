package com.myfoodhouse.restaurant.service.domain.core.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.myfoodhouse.restaurant.service.domain.core.entity.OrderApproval;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;
import com.myfoodhouse.sys.domain.valueobjects.RestaurantId;

public class OrderApprovedEvent extends OrderApprovalEvent{

  
    private final DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher;

    public OrderApprovedEvent(OrderApproval orderApproval,
                              RestaurantId restaurantId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt,
                              DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher) {
        super(orderApproval, restaurantId, failureMessages, createdAt);
        this.orderApprovedEventDomainEventPublisher = orderApprovedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderApprovedEventDomainEventPublisher.publish(this);
    }
}
