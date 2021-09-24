package org.reactive.programming.produmer.consumer.repository;

import org.reactive.programming.produmer.consumer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
