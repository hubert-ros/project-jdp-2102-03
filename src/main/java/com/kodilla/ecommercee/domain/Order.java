package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "order")
public class Order {
    enum OrderStatus {
        UNPAID,
        PAID,
        CANCELED
    }

    @Id
    @Column(name = "order_id")
    private Long id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

}
