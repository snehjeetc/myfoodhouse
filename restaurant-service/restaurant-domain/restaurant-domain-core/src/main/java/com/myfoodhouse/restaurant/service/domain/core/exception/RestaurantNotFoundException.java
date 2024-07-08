package com.myfoodhouse.restaurant.service.domain.core.exception;

import com.myfoodhouse.sys.domain.exception.DomainException;

public class RestaurantNotFoundException extends DomainException{

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
