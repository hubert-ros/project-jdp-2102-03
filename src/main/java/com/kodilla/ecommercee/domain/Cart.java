package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CARTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

        @Id
        @NotNull
        @GeneratedValue
        @Column(name = "CART_ID", unique = true)
        private Long cartId;

        @OneToMany(
                targetEntity = Product.class,
                mappedBy = "carts",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY
        )
        public List<Product> productsInCart = new ArrayList<>();
}


