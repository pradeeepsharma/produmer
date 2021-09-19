package org.reactive.programming.produmer.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.reactive.programming.produmer.producer.domain.Customer;
import org.reactive.programming.produmer.producer.domain.Product;
import org.reactive.programming.produmer.producer.exception.NoValidFileException;
import org.reactive.programming.produmer.producer.repository.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class KafkaPublisherService implements PublisherService {
    private List<Customer> customers;
    private List<Product> products;
    @Autowired
    DataLoader dataLoader;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.customer}")
    String customer_topic;

    @Value("${kafka.topic.product}")
    String product_topic;

    public KafkaPublisherService(List<Customer> customers, List<Product> products){
        this.customers = customers;
        this.products = products;
    }

    @Override
    public void uploadCustomerData() {
        System.out.println("Customer data being upload "+customers.size());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataLoader.loadNewCustomerData();
            customers.stream().forEach(customer -> {
                try {
                    sendMessage(customer_topic, objectMapper.writeValueAsString(customer));
                } catch (JsonProcessingException e) {
                    System.out.println("Could not convert to string :"+customer);
                }
            });
        } catch (NoValidFileException e) {
            System.out.println("Not sending any customer to topic");
            e.printStackTrace();
        }
    }

    @Override
    public void uploadProductData() {
        System.out.println("Product date being upload :"+products.size());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataLoader.loadNewProductData();
            products.stream().forEach(product -> {
                try {
                    sendMessage(product_topic, objectMapper.writeValueAsString(product));
                } catch (JsonProcessingException e) {
                    System.out.println("Could not convert to string :"+product);
                }
            });
        } catch (NoValidFileException e) {
            System.out.println("Not sending any product to topic");
            e.printStackTrace();
        }
    }

    protected void sendMessage(String topicName, String objectAsString) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, objectAsString);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent object with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send object  due to : " + ex.getMessage());
            }
        });
    }


}
