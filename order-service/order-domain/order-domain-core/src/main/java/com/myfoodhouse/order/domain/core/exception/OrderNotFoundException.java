package com.myfoodhouse.order.domain.core.exception;

public class OrderNotFoundException extends OrderDomainException{
    
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable throwable) { 
        super(message, throwable); 
    }
    
}
