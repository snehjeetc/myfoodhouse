package com.myfoodhouse.restaurant.service.domain.ports.output.message.kafka;

import com.myfoodhouse.restaurant.service.domain.core.event.OrderRejectedEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface OrderRejectedMessagePublisher extends DomainEventPublisher<OrderRejectedEvent>{
    
}
