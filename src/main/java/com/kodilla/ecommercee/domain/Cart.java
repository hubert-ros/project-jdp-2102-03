package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "CARTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

        @Id
        @GeneratedValue
        @Column(name = "CART_ID", unique = true)
        private Long cartId;

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "ORDER-ID")
        private Order order;

        @ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
        @JoinTable(
                name = "PRODUCTS_IN_CART",
                joinColumns = @JoinColumn(name = "CART_ID"),
                inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
        )
        public Set<Product> products = new HashSet<>();
}


