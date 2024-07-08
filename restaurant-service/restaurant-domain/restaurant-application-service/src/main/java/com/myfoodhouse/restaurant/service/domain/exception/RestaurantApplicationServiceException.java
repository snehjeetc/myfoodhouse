package com.myfoodhouse.restaurant.service.domain.exception;

import com.myfoodhouse.sys.domain.exception.DomainException;

public class RestaurantApplicationServiceException extends DomainException{

    public RestaurantApplicationServiceException(String message) {
        super(message);
    }

    
    public RestaurantApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
