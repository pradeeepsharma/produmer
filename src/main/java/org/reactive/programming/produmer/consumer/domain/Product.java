package org.reactive.programming.produmer.consumer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product implements Serializable {
    @NonNull
    @Id
    private Long id;
    private Long attrNumber;
    private String name;
    private String department;


}
