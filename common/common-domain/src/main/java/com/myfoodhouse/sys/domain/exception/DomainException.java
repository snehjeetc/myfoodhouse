package com.myfoodhouse.sys.domain.exception;

public class DomainException extends RuntimeException {
    
    public DomainException(String message) { 
        super(message); 
    }

    public DomainException(String message, Throwable throwable) { 
        super(message, throwable); 
    }
}
