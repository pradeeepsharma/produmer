package org.reactive.programming.produmer.producer.service;

import org.reactive.programming.produmer.producer.domain.Customer;
import org.reactive.programming.produmer.producer.domain.Product;
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
    private KafkaTemplate<String, Object> kafkaTemplate;

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
        try {
            dataLoader.loadNewCustomerData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        customers.stream().forEach(customer -> sendMessage(customer_topic, customer));
    }

    @Override
    public void uploadProductData() {
        System.out.println("Product date being upload :"+products.size());
        try {
            dataLoader.loadNewProductData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        products.stream().forEach(product -> sendMessage(product_topic, product));
    }

    private void sendMessage(String topicName, Object object) {

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, object);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent object with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send object  due to : " + ex.getMessage());
            }
        });
    }


}
