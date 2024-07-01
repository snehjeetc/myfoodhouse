package com.myfoodhouse.ports.output.message.publisher.payment;

import com.myfoodhouse.order.domain.core.event.OrderCancelledEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface OrderCancelledPaymetRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent>{
    
}
