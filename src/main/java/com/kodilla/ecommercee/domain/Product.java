package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PRODUCTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToMany(mappedBy = "products")
    private Set<Cart> cartOfProduct = new HashSet<>();
}
