package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserName(),
                userDto.getEMail(),
                userDto.getAddress(),
                userDto.isBlocked(),
                userDto.getOrders()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserName(),
                user.getEMail(),
                user.getAddress(),
                user.isBlocked(),
                user.getOrders()
        );
    }
}
