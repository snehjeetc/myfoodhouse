package com.myfoodhouse.payment.service.domain.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.myfoodhouse.sys.domain.valueobjects.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {
      private String id;
    private String sagaId;
    private String orderId;
    private String customerId;
    private BigDecimal price;
    private Instant createdAt;
    private PaymentStatus paymentOrderStatus;

    public void setPaymentOrderStatus(PaymentStatus paymentOrderStatus) {
        this.paymentOrderStatus = paymentOrderStatus;
    }
}
