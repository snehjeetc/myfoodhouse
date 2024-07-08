package com.myfoodhouse.restaurant.service.domain.core;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.myfoodhouse.restaurant.service.domain.core.entity.Restaurant;
import com.myfoodhouse.restaurant.service.domain.core.event.OrderApprovalEvent;
import com.myfoodhouse.restaurant.service.domain.core.event.OrderApprovedEvent;
import com.myfoodhouse.restaurant.service.domain.core.event.OrderRejectedEvent;
import com.myfoodhouse.sys.domain.constants.DomainConstants;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;
import com.myfoodhouse.sys.domain.valueobjects.OrderApprovalStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestaurantDomainServiceImpl implements RestaurantDomainService{

   @Override
    public OrderApprovalEvent validateOrder(Restaurant restaurant,
                                            List<String> failureMessages,
                                            DomainEventPublisher<OrderApprovedEvent>
                                                    orderApprovedEventDomainEventPublisher,
                                            DomainEventPublisher<OrderRejectedEvent>
                                                    orderRejectedEventDomainEventPublisher) {
        restaurant.validateOrder(failureMessages);
        log.info("Validating order with id: {}", restaurant.getOrderDetail().getId().getValue());

        if (failureMessages.isEmpty()) {
            log.info("Order is approved for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(restaurant.getOrderApproval(),
                    restaurant.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                    orderApprovedEventDomainEventPublisher);
        } else {
            log.info("Order is rejected for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.REJECTED);
            return new OrderRejectedEvent(restaurant.getOrderApproval(),
                    restaurant.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                    orderRejectedEventDomainEventPublisher);
        }
    }
    
}
