package com.myfoodhouse.restaurant.service.domain.core.exception;

import com.myfoodhouse.sys.domain.exception.DomainException;

public class RestaurantDomainException extends DomainException{
    
    public RestaurantDomainException(String message) {
        super(message);
    }
    
    public RestaurantDomainException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
