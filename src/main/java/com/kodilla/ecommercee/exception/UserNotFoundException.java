package com.kodilla.ecommercee.exception;

import com.kodilla.ecommercee.domain.UserDto;

public class UserNotFoundException extends Exception {

    private UserDto nullUser = new UserDto(null, null, null,false, null);

    public UserNotFoundException() {
        super("USER NOT FOUND IN DATABASE");
    }

    public UserDto getNullUser() {
        return nullUser;
    }
}
