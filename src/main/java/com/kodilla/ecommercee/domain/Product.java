package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "PRODUCTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "PRODUCT_ID", unique = true)
    private Long productId;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "PRODUCTS_IN_CART")
    private Cart carts;
}
