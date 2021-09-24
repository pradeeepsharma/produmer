package org.reactive.programming.produmer.consumer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer implements Serializable {

    @NonNull
    @Id
    private Long id;
    private Long attrNumber;
    @NonNull
    private String name;
    private String registeredDate;
    @OneToMany(targetEntity=Product.class, mappedBy="id", fetch= FetchType.EAGER)
    private List<Product> products;
}
