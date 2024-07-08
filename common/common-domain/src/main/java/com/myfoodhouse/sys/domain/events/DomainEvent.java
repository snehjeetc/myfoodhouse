package com.myfoodhouse.sys.domain.events;

public interface DomainEvent<T> {
    void fire();
}
