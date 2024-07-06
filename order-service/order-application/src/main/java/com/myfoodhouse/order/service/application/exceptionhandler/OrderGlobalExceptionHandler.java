package com.myfoodhouse.order.service.application.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myfoodhouse.order.domain.core.exception.OrderDomainException;
import com.myfoodhouse.order.domain.core.exception.OrderNotFoundException;
import com.myfoodhouse.system.exceptionhandler.ErrorDTO;
import com.myfoodhouse.system.exceptionhandler.OrderSystemGlobalExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends OrderSystemGlobalExceptionHandler{
    
    @ResponseBody
    @ExceptionHandler(value = {OrderDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(OrderDomainException orderDomainException) { 
        log.error(orderDomainException.getMessage(), orderDomainException); 
        return ErrorDTO.builder()
                       .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                       .message(orderDomainException.getMessage())
                       .build(); 
    }

    @ResponseBody
    @ExceptionHandler(value = {OrderNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(OrderNotFoundException orderNotFoundException) { 
        log.error(orderNotFoundException.getMessage(), orderNotFoundException); 
        return ErrorDTO.builder()
                       .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                       .message(orderNotFoundException.getMessage())
                       .build(); 
    }


}
