package com.myfoodhouse.restaurant.service.domain.core;

import java.util.List;

import com.myfoodhouse.restaurant.service.domain.core.entity.Restaurant;
import com.myfoodhouse.restaurant.service.domain.core.event.OrderApprovalEvent;
import com.myfoodhouse.restaurant.service.domain.core.event.OrderApprovedEvent;
import com.myfoodhouse.restaurant.service.domain.core.event.OrderRejectedEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface RestaurantDomainService {
    
    OrderApprovalEvent validateOrder(Restaurant restaurant,
                                     List<String> failureMessages,
                                     DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
                                     DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher);
}
