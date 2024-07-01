package com.myfoodhouse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.myfoodhouse.order.domain.core.event.OrderCreatedEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent>{

    private ApplicationEventPublisher applicationEventPublisher; 

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("Order created event is published for order id : {}", domainEvent.getOrder().getId().getValue());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher; 
    }
    
}
