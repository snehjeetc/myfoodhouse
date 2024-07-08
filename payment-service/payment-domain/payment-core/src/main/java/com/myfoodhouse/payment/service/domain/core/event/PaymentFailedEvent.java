package com.myfoodhouse.payment.service.domain.core.event;

import java.time.ZonedDateTime;
import java.util.List;

import com.myfoodhouse.payment.service.domain.core.entity.Payment;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public class PaymentFailedEvent extends PaymentEvent{

    
    private final DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher;

    public PaymentFailedEvent(Payment payment,
                              ZonedDateTime createdAt,
                              List<String> failureMessages,
                              DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {
        super(payment, createdAt, failureMessages);
        this.paymentFailedEventDomainEventPublisher = paymentFailedEventDomainEventPublisher;
    }

    @Override
    public void fire() {

    }
    
}
