package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCTS")
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
    private BigDecimal price;


    @ManyToMany(mappedBy = "products")
    private Set<Group> groupsOfProduct = new HashSet<>();

    @ManyToMany(mappedBy = "products")

    private List<Cart> carts = new ArrayList<>();

    public Product(@NotNull String productName, @NotNull String productDescription, @NotNull BigDecimal price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }
}
