package org.reactive.programming.produmer.producer.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
public class DataLoaderTest {

    private FileDataLoader dataLoader;

    @BeforeEach
    private void setup(){
        dataLoader = new FileDataLoader();
    }

    @Test
    public void can_load_customers_from_file() throws IOException {
        dataLoader.loadNewCustomerData();
        assertEquals(2,dataLoader.getCustomers().size());
    }

    @Test
    public void can_load_products_from_file() throws IOException {
        dataLoader.loadNewProductData();
        assertEquals(4, dataLoader.getProducts().size());
    }
}
