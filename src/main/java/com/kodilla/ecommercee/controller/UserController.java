package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @PostMapping(value = "createUser")
    public GenericEntity createUser() {
        return new GenericEntity("user_1");
    }

    @PutMapping(value = "blockUser")
    public GenericEntity blockUser(Long userId) {
        return new GenericEntity("user_blocked");
    }

    @PostMapping(value = "createUserKey")
    public String createUserKey(String userName, String userKey) {
        return "user_key";
    }
}