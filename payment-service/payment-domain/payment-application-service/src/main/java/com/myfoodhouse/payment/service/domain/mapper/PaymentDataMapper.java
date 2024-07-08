package com.myfoodhouse.payment.service.domain.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.myfoodhouse.payment.service.domain.core.entity.Payment;
import com.myfoodhouse.payment.service.domain.dto.PaymentRequest;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;
import com.myfoodhouse.sys.domain.valueobjects.Money;
import com.myfoodhouse.sys.domain.valueobjects.OrderId;

@Component
public class PaymentDataMapper {
      public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }
}
