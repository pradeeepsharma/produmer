package org.reactive.programming.produmer.consumer.repository;

import org.reactive.programming.produmer.consumer.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
