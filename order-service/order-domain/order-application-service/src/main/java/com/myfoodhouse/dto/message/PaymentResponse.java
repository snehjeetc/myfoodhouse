package com.myfoodhouse.dto.message;

import java.math.BigDecimal;
import java.time.Instant;

import com.myfoodhouse.sys.domain.valueobjects.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {
    
    private String id; 
    private String sagaId; 
    private String orderId; 
    private String paymentId; 
    private String customerId; 
    private BigDecimal price; 
    private Instant createdAt; 
    private PaymentStatus paymentStatus; 
}
