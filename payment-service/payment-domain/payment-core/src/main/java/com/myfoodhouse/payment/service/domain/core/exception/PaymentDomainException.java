package com.myfoodhouse.payment.service.domain.core.exception;

import com.myfoodhouse.sys.domain.exception.DomainException;

public class PaymentDomainException extends DomainException{
    
    public PaymentDomainException(String message) {
        super(message);
    }

    public PaymentDomainException(String message, Throwable throwable){ 
        super(message, throwable); 
    }
    
}
