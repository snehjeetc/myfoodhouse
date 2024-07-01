package com.myfoodhouse.sys.domain.events.publisher;

import com.myfoodhouse.sys.domain.events.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent); 
}
