package com.myfoodhouse.ports.output.message.publisher.payment;

import com.myfoodhouse.order.domain.core.event.OrderCreatedEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent>{
    
}
