package org.reactive.programming.produmer.producer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class Customer implements Serializable {

    @NonNull
    private Long id;
    private Long attrNumber;
    @NonNull
    private String name;
    private List<Product> products;
}
