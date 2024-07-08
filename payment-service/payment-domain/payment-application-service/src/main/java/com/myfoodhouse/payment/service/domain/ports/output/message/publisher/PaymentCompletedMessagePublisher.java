package com.myfoodhouse.payment.service.domain.ports.output.message.publisher;

import com.myfoodhouse.payment.service.domain.core.event.PaymentCompletedEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface  PaymentCompletedMessagePublisher extends DomainEventPublisher<PaymentCompletedEvent>{
    
}
