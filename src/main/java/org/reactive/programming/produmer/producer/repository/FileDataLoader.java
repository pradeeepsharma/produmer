package org.reactive.programming.produmer.producer.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.reactive.programming.produmer.producer.domain.Customer;
import org.reactive.programming.produmer.producer.domain.Product;
import org.reactive.programming.produmer.producer.exception.NoValidFileException;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Repository
public class FileDataLoader implements DataLoader {
    private static final String CUSTOMER_FILE_NAME = "customers.txt";
    private static final String PRODUCT_FILE_NAME = "products.txt";
    private List<Customer> customers = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    @Bean
    public List<Customer> getCustomers() {
        return customers;
    }

    @Bean
    public List<Product> getProducts() {
        return products;
    }

    public void loadNewCustomerData() throws NoValidFileException {

        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(new ClassPathResource(CUSTOMER_FILE_NAME).getFile())) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray customerArray = (JSONArray) jsonObject.get("customers");
            customers = mapper.readValue(customerArray.toJSONString(), new TypeReference<List<Customer>>() {
            });

        } catch (IOException | ParseException ie) {
            throw new NoValidFileException(ie, "There is no valid file present with the file name specified");
        }

    }

    @Override
    public void loadNewProductData() throws NoValidFileException {
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(new ClassPathResource(PRODUCT_FILE_NAME).getFile())) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray productArray = (JSONArray) jsonObject.get("products");
            products = mapper.readValue(productArray.toJSONString(), new TypeReference<List<Product>>() {
            });

        } catch (IOException | ParseException ie) {
            throw new NoValidFileException(ie, "There is no valid file present with the file name specified");
        }

    }


}
