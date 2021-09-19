package org.reactive.programming.produmer.consumer.service;

import org.reactive.programming.produmer.consumer.repository.CustomerRepository;
import org.reactive.programming.produmer.consumer.repository.ProductRepository;
import org.reactive.programming.produmer.consumer.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    private JsonUtil jsonUtil = new JsonUtil();

    @KafkaListener(topics = "bank-customers", groupId = "customer", containerFactory = "kafkaListenerContainerFactory")
    public void getCustomerData(String message) {

        System.out.println("Received customer :"+message);
        customerRepository.save(jsonUtil.getCustomer(message));

    }


    @KafkaListener(topics = "bank-products", groupId = "product", containerFactory = "kafkaListenerContainerFactory")
    public void getProductData(String message) {
        System.out.println("Received product :"+message);
        productRepository.save(jsonUtil.getProduct(message));
    }
}
