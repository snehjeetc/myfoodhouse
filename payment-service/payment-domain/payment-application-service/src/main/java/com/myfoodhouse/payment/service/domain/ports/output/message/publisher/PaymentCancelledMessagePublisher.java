package com.myfoodhouse.payment.service.domain.ports.output.message.publisher;

import com.myfoodhouse.payment.service.domain.core.event.PaymentCancelledEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent>{
    
}
