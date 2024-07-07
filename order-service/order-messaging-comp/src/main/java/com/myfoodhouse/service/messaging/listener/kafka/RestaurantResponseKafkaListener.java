package com.myfoodhouse.service.messaging.listener.kafka;

import java.util.List;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.myfoodhouse.kafka.consumer.KafkaConsumer;
import com.myfoodhouse.kafka.order.avro.model.OrderApprovalStatus;
import com.myfoodhouse.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.myfoodhouse.order.domain.core.entity.Order;
import com.myfoodhouse.ports.input.message.listener.restaurantapproval.RestaurantResponseMessageListener;
import com.myfoodhouse.service.messaging.mapper.OrderMessagingDataMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestaurantResponseKafkaListener implements KafkaConsumer<RestaurantApprovalResponseAvroModel>{

    private final RestaurantResponseMessageListener restaurantResponseMessageListener; 
    private final OrderMessagingDataMapper orderMessagingDataMapper; 


    public RestaurantResponseKafkaListener(RestaurantResponseMessageListener restaurantResponseMessageListener,
            OrderMessagingDataMapper orderMessagingDataMapper) {
        this.restaurantResponseMessageListener = restaurantResponseMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }


    @Override
    public void receive(
                        @Payload List<RestaurantApprovalResponseAvroModel> messages, 
                        @Header(KafkaHeaders.RECEIVED_KEY) List<Long> keys, 
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions, 
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
                            log.info("{} number of restaurant approval responses received with keys {}, partitions {} and offsets {}",
                            messages.size(),
                            keys.toString(),
                            partitions.toString(),
                            offsets.toString());
            
                    messages.forEach(restaurantApprovalResponseAvroModel -> {
                        if (OrderApprovalStatus.APPROVED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()) {
                            log.info("Processing approved order for order id: {}",
                                    restaurantApprovalResponseAvroModel.getOrderId());
                                    restaurantResponseMessageListener.orderApproved(orderMessagingDataMapper
                                    .approvalResponseAvroModelToApprovalResponse(restaurantApprovalResponseAvroModel));
                        } else if (OrderApprovalStatus.REJECTED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()) {
                            log.info("Processing rejected order for order id: {}, with failure messages: {}",
                                    restaurantApprovalResponseAvroModel.getOrderId(),
                                    String.join(Order.FAILURE_MESSAGE_DELIMETER,
                                            restaurantApprovalResponseAvroModel.getFailureMessages()));
                                            restaurantResponseMessageListener.orderRejected(orderMessagingDataMapper
                                    .approvalResponseAvroModelToApprovalResponse(restaurantApprovalResponseAvroModel));
                        }
                    });       
    }
    
}
