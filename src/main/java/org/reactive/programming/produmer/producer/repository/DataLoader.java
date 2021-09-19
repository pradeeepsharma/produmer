package org.reactive.programming.produmer.producer.repository;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataLoader {
    void loadNewCustomerData() throws FileNotFoundException;
    void loadNewProductData() throws FileNotFoundException;
}
