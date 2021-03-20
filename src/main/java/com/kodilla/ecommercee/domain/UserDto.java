package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UserDto {

    private Long userId;
    private String userName;
    private String eMail;
    private String address;
    private boolean blocked;
    private Set<Order> orders;
}
