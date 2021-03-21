package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "createUser")
    public UserDto createUser(@RequestParam String username, @RequestParam String eMail, @RequestParam String address) {

        User user = new User(username, eMail, address);
        return userMapper.mapToUserDto(userService.saveUser(user));
    }

    @PutMapping(value = "blockUser")
    public UserDto blockUser(@RequestParam Long userId) {

        try {
            return userMapper.mapToUserDto(userService.blockUser(userId));
        } catch(UserNotFoundException e) {
            return e.getNullUser();
        }

    }

    @PostMapping(value = "createUserKey")
    public String createUserKey(@RequestParam Long userId) {

        try {
            return userService.createUserKey(userId);
        } catch(UserNotFoundException e) {
            return e.getMessage();
        }
    }
}