package com.myfoodhouse.payment.service.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import com.myfoodhouse.payment.service.domain.core.entity.Payment;

public interface PaymentRepository {
    
    Payment save(Payment payment);

    Optional<Payment> findByOrderId(UUID orderId);
}
