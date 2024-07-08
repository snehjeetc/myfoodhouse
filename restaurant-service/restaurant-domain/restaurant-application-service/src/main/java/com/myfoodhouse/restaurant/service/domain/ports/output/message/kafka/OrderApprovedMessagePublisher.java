package com.myfoodhouse.restaurant.service.domain.ports.output.message.kafka;

import com.myfoodhouse.restaurant.service.domain.core.event.OrderApprovedEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface OrderApprovedMessagePublisher extends DomainEventPublisher<OrderApprovedEvent>{
    
}
