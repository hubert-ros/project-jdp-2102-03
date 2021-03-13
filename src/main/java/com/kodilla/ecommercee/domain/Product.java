package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    private String productName;

    @NotNull
    private String productDescription;

    @NotNull
    private Double price;

    @ManyToMany(mappedBy = "products")
    private Set<Group> groupsOfProduct = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts = new ArrayList<>();

}

