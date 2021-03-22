package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Order {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;
  
}
