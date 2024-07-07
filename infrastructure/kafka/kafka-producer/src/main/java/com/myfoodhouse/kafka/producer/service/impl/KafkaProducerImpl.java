package com.myfoodhouse.kafka.producer.service.impl;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.myfoodhouse.kafka.producer.exception.KafkaProducerException;
import com.myfoodhouse.kafka.producer.service.KafkaProducer;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback) {
        log.info("Sending message={} to topic={}", message, topicName);
        try {
            CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.whenComplete(callback); 
        } catch (KafkaException exception) {
            log.error("Error on kafka producer on key : {}, message : {}, and exception : {}", key, message, exception.getMessage());
            throw new KafkaProducerException("Error on kafka producer -> key : " + key + ", message : " + message); 
        }
    }

    @PreDestroy
    public void close() { 
        if(kafkaTemplate != null) { 
            log.info("Closing kafka producer");
            kafkaTemplate.destroy();
        }
    }

}
