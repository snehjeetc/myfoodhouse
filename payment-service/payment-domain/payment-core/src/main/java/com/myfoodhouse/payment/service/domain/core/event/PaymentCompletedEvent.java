package com.myfoodhouse.payment.service.domain.core.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import com.myfoodhouse.payment.service.domain.core.entity.Payment;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public class PaymentCompletedEvent extends PaymentEvent{

    
    private final DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher;

    public PaymentCompletedEvent(Payment payment,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher) {
        super(payment, createdAt, Collections.emptyList());
        this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
       paymentCompletedEventDomainEventPublisher.publish(this);
    }
    
}
