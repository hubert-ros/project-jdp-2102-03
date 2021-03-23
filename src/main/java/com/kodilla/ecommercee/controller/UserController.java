package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {

        User user = userMapper.mapToUser(userDto);
        return userMapper.mapToUserDto(userService.saveUser(user));
    }

    @PutMapping(value = "blockUser")
    public UserDto blockUser(@RequestParam Long userId) throws UserNotFoundException {

            return userMapper.mapToUserDto(userService.blockUser(userId));
    }

    @PostMapping(value = "createUserKey")
    public String createUserKey(@RequestParam String userName, @RequestParam String eMail) throws UserNotFoundException {

            return userService.createUserKey(userName, eMail);
    }
}