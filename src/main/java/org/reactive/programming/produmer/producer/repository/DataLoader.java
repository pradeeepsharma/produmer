package org.reactive.programming.produmer.producer.repository;

import org.json.simple.parser.ParseException;
import org.reactive.programming.produmer.producer.exception.NoValidFileException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataLoader {
    void loadNewCustomerData() throws NoValidFileException;
    void loadNewProductData() throws NoValidFileException;
}
