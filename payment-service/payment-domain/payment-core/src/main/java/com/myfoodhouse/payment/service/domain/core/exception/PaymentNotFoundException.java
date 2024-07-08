package com.myfoodhouse.payment.service.domain.core.exception;

import com.myfoodhouse.sys.domain.exception.DomainException;

public class PaymentNotFoundException extends DomainException{
    
    public PaymentNotFoundException(String message) {
        super(message);
    }

    
    public PaymentNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
