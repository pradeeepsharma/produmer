package org.reactive.programming.produmer.consumer.repository;

import org.reactive.programming.produmer.consumer.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
}
