package com.myfoodhouse;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.myfoodhouse.order.domain.core.event.OrderCreatedEvent;
import com.myfoodhouse.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCreatedEventApplicationListener {
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderCreatedEventApplicationListener(
            OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    } 

    //this method will only be called, when the method that publishes the 
    //ApplicationEventPublisher is completed and the transaction is committed.  
    @TransactionalEventListener
    void process(OrderCreatedEvent domainEvent) { 

    }
}
