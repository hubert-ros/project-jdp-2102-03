package com.kodilla.ecommercee.domain;

import lombok.Getter;

import java.util.Set;

@Getter
public class UserDto {

    private String userName;
    private String eMail;
    private String address;
    private boolean blocked;
    private Set<Order> orders;

    public UserDto(String userName, String eMail, String address, boolean blocked, Set<Order> orders) {
        this.userName = userName;
        this.eMail = eMail;
        this.address = address;
        this.blocked = blocked;
        this.orders = orders;
    }
}
