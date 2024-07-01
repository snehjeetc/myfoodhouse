package com.myfoodhouse.order.domain.core.exception;

import com.myfoodhouse.sys.domain.exception.DomainException;

public class OrderDomainException extends DomainException {

    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable throwable) { 
        super(message, throwable); 
    }
}
