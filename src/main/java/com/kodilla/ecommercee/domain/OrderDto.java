package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private Cart cart;
    private User user;
    private OrderStatus status;

}
