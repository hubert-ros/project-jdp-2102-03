package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Order {

    public enum OrderStatus {
        UNPAID,
        PAID,
        CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    //@NotNull
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //@NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

}
