package com.myfoodhouse.service.messaging.publisher.kafka;

import org.springframework.stereotype.Component;

import com.myfoodhouse.config.OrderServiceConfig;
import com.myfoodhouse.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.myfoodhouse.kafka.producer.KafkaMessageHelper;
import com.myfoodhouse.kafka.producer.service.KafkaProducer;
import com.myfoodhouse.order.domain.core.event.OrderPaidEvent;
import com.myfoodhouse.ports.output.message.publisher.restaurant.OrderPaidRestaurantRequestPublisher;
import com.myfoodhouse.service.messaging.mapper.OrderMessagingDataMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestPublisher{

    private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer; 
    private final OrderMessagingDataMapper orderMessagingDataMapper; 
    private final OrderServiceConfig orderServiceConfigData; 
    private final KafkaMessageHelper kafkaHelper; 

    

    public PayOrderKafkaMessagePublisher(KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer,
            OrderMessagingDataMapper orderMessagingDataMapper, OrderServiceConfig orderServiceConfigData,
            KafkaMessageHelper kafkaHelper) {
        this.kafkaProducer = kafkaProducer;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaHelper = kafkaHelper;
    }



    @Override
    public void publish(OrderPaidEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderCreatedEvent for order id: {}", orderId);

        try {
            RestaurantApprovalRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
                    .orderPaidEventToRestaurantApprovalRequestAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    orderId,
                    paymentRequestAvroModel,
                    kafkaHelper.getKafkaCallback(
                        orderServiceConfigData.getPaymentResponseTopicName(),
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
