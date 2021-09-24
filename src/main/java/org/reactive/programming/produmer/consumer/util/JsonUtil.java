package org.reactive.programming.produmer.consumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.reactive.programming.produmer.consumer.domain.Customer;
import org.reactive.programming.produmer.consumer.domain.Product;

public class JsonUtil {
    public Customer getCustomer(String objectAsJson) {
        Customer customer = null;
        ObjectMapper objectMapper = new JsonMapper();
        try {
            customer = objectMapper.readValue(objectAsJson, Customer.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Product getProduct(String objectAsJson) {
        Product product=null;
        ObjectMapper objectMapper = new JsonMapper();
        try {
            product = objectMapper.readValue(objectAsJson, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return product;
    }
}
