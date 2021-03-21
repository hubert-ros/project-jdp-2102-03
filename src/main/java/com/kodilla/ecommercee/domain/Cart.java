package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CARTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "CART_ID")
        private Long cartId;

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "ORDER_ID")
        private Order order;

        @ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
        @JoinTable(
                name = "PRODUCTS_IN_CART",
                joinColumns = @JoinColumn(name = "CART_ID"),
                inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
        )
        public List<Product> products = new ArrayList<>();

        @Column(name = "VALUE")
        private BigDecimal value;

        public Cart(Long cartId, Order order, BigDecimal value) {
                this.cartId = cartId;
                this.order = order;
                this.value = value;
        }

        public Cart(BigDecimal value) {
                this.value = value;
        }
}


