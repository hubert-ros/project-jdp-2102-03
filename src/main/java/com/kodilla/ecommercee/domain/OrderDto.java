package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDto {

    public enum OrderStatus {
        UNPAID,
        PAID,
        CANCELED
    }

    private Long id;
    private Cart cart;
    private User user;
    private OrderDto.OrderStatus status;

}
