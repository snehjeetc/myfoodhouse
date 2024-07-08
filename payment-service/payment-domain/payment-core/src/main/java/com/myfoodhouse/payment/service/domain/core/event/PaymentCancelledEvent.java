package com.myfoodhouse.payment.service.domain.core.event;

import java.time.ZonedDateTime;
import java.util.Collections;

import com.myfoodhouse.payment.service.domain.core.entity.Payment;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public class PaymentCancelledEvent extends PaymentEvent {

    private final DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher;

    public PaymentCancelledEvent(Payment payment,
            ZonedDateTime createdAt,
            DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher) {
        super(payment, createdAt, Collections.emptyList());
        this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        paymentCancelledEventDomainEventPublisher.publish(this);
    }

}
