package com.myfoodhouse.service.messaging.publisher.kafka;

import org.springframework.stereotype.Component;

import com.myfoodhouse.config.OrderServiceConfig;
import com.myfoodhouse.kafka.order.avro.model.PaymentRequestAvroModel;
import com.myfoodhouse.kafka.producer.KafkaMessageHelper;
import com.myfoodhouse.kafka.producer.service.KafkaProducer;
import com.myfoodhouse.order.domain.core.event.OrderCreatedEvent;
import com.myfoodhouse.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.myfoodhouse.service.messaging.mapper.OrderMessagingDataMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {

    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final OrderServiceConfig orderServiceConfigData;
    private final KafkaMessageHelper kafkaHelper;

    public CreateOrderKafkaMessagePublisher(KafkaProducer<String, PaymentRequestAvroModel> kafkaTemplate,
            OrderMessagingDataMapper orderMessagingDataMapper, OrderServiceConfig orderServiceConfigData,
            KafkaMessageHelper kafkaHelper) {
        this.kafkaProducer = kafkaTemplate;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaHelper = kafkaHelper;
    }

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderCreatedEvent for order id: {}", orderId);

        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
                    .orderCreatedEventToPaymentRequestAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    orderId,
                    paymentRequestAvroModel,
                    kafkaHelper
                            .getKafkaCallback(orderServiceConfigData.getPaymentResponseTopicName(),
                                    paymentRequestAvroModel,
                                    orderId,
                                    "PaymentRequestAvroModel"));

            log.info("PaymentRequestAvroModel sent to Kafka for order id: {}", paymentRequestAvroModel.getOrderId());
        } catch (Exception e) {
           log.error("Error while sending PaymentRequestAvroModel message" +
                   " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }

}