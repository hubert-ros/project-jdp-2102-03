package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CARTS")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "order_id")
    private Cart cart;
}
