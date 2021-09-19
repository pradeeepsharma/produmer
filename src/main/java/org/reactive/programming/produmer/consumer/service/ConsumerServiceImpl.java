package org.reactive.programming.produmer.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl {

    @KafkaListener(topics = "bank-customers", groupId = "customer", containerFactory = "kafkaListenerContainerFactory")
    public void getCustomerData(String message) {
        System.out.println("Received customer :"+message);
    }


    @KafkaListener(topics = "bank-products", groupId = "product", containerFactory = "kafkaListenerContainerFactory")
    public void getProductData(String message) { System.out.println("Received product :"+message); }
}
