package com.myfoodhouse.ports.input.message.listener.payment;

import com.myfoodhouse.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
    void paymentCompleted(PaymentResponse response); 
    void paymentCancelled(PaymentResponse response); 
}
