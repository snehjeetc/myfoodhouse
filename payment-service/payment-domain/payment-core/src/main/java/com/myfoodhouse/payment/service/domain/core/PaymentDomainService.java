package com.myfoodhouse.payment.service.domain.core;

import java.util.List;

import com.myfoodhouse.payment.service.domain.core.entity.CreditEntry;
import com.myfoodhouse.payment.service.domain.core.entity.CreditHistory;
import com.myfoodhouse.payment.service.domain.core.entity.Payment;
import com.myfoodhouse.payment.service.domain.core.event.PaymentCancelledEvent;
import com.myfoodhouse.payment.service.domain.core.event.PaymentCompletedEvent;
import com.myfoodhouse.payment.service.domain.core.event.PaymentEvent;
import com.myfoodhouse.payment.service.domain.core.event.PaymentFailedEvent;
import com.myfoodhouse.sys.domain.events.publisher.DomainEventPublisher;

public interface PaymentDomainService {
    
    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent>
                                                    paymentCompletedEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages, DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
