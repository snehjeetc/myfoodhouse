package com.myfoodhouse.system.exceptionhandler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class OrderSystemGlobalExceptionHandler {
    
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleException(Exception exc) { 
        log.error("Unexpected Excpection : {}", exc.getMessage()); 
        return ErrorDTO.builder()
                       .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                       .message("Unexpected Error")
                       .build(); 
    }

    @ResponseBody
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ValidationException validationException){ 
        ErrorDTO errorDTO;
        if(validationException instanceof ConstraintViolationException constraintViolationException){ 
            String violations = constraintViolationException.getConstraintViolations().stream()
                                .map(constraintViolation -> constraintViolation.getMessage())
                                .collect(Collectors.joining("--"));                    
            log.error("Violations caught: {}", violations);                     
            errorDTO = ErrorDTO.builder()
                               .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                               .message(violations)
                               .build(); 
        } else { 
            log.error(validationException.getMessage(), validationException);
            errorDTO = ErrorDTO.builder()
                              .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                              .message(validationException.getMessage())
                              .build();
        }
        return errorDTO; 
    }
}
