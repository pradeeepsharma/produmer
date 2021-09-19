package org.reactive.programming.produmer.producer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Product implements Serializable {
    @NonNull
    private int id;
    private int attrNumber;
    private String name;
    private String department;


}
