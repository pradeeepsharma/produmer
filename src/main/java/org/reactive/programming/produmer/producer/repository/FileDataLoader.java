package org.reactive.programming.produmer.producer.repository;

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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

        try (FileReader reader = new FileReader(new ClassPathResource(CUSTOMER_FILE_NAME).getFile())) {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray customerArray = (JSONArray) jsonObject.get("customers");
            Iterator customerIterator = customerArray.iterator();

            while (customerIterator.hasNext()) {
                JSONObject customerJsonObject = (JSONObject) customerIterator.next();
                Customer customer = Customer.builder().id((Long) customerJsonObject.get("id")).
                        name((String) customerJsonObject.get("name")).
                        attrNumber((Long)customerJsonObject.get("attrNumber")).
                        build();
                JSONArray productArray = (JSONArray) customerJsonObject.get("products");
                Iterator productIterator = productArray.iterator();

                List<Product> customerProducts = new ArrayList<>();
                while (productIterator.hasNext()) {
                    JSONObject productJsonObject = (JSONObject) productIterator.next();
                    customerProducts.add(Product.builder().id((Long) productJsonObject.get("id")).build());
                }
                customer.setProducts(customerProducts);
                customers.add(customer);
            }
        } catch (IOException | ParseException ie) {
            throw new NoValidFileException(ie, "There is no valid file present with the file name specified");
        }

    }

    @Override
    public void loadNewProductData() throws NoValidFileException {
        try (FileReader reader = new FileReader(new ClassPathResource(PRODUCT_FILE_NAME).getFile())) {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray productArray = (JSONArray) jsonObject.get("products");
            Iterator productIterator = productArray.iterator();

            while (productIterator.hasNext()) {
                JSONObject productJsonObject = (JSONObject) productIterator.next();
                Product product = Product.builder().id((Long) productJsonObject.get("id")).
                        name((String) productJsonObject.get("name")).
                        attrNumber((Long) productJsonObject.get("attrNumber")).
                        department((String) productJsonObject.get("department")).
                        build();


                products.add(product);
            }
        } catch (IOException | ParseException ie) {
            throw new NoValidFileException(ie, "There is no valid file present with the file name specified");
        }

    }


/*
    @Bean
    public List<Product> getProducts() {
        return products;
    }
*/
    /*@Override
    public void loadDataFromFile(String fileName) {

        try {
            loadCustomers().stream().forEach(customer -> customers.add(Customer.Builder.newInstance().setId(customer.getId()).setName(customer.getName()).setAttrNumber(customer.getAttrNumber()).setProducts(customer.getProducts()).build()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void loadNewProductData() {

        try {
            loadProducts().stream().forEach(product -> products.add(Product.Builder.newInstance().setId(product.getId()).setName(product.getName()).setDepartment(product.getDepartment()).setAttrNumber(product.getAttrNumber()).build()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    /*protected List<CustomerModel> loadCustomers() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File fileResource = new ClassPathResource("customers.txt").getFile();

        return objectMapper.readValue(fileResource, new TypeReference<List<CustomerModel>>() {
        });
    }

    protected List<ProductModel> loadProducts() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File fileResource = new ClassPathResource("products.txt").getFile();

        return objectMapper.readValue(fileResource, new TypeReference<List<ProductModel>>() {
        });
    }
*/


}
