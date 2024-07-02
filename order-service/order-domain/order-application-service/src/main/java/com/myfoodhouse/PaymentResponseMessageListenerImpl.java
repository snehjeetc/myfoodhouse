package com.myfoodhouse;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.myfoodhouse.dto.message.PaymentResponse;
import com.myfoodhouse.ports.input.message.listener.payment.PaymentResponseMessageListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Service 
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
    
    @Override
    public void paymentCompleted(PaymentResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void paymentCancelled(PaymentResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
