package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Random;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public User blockUser(final Long id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setBlocked(true);
        return userRepository.save(user);
    }

    public String createUserKey(final Long id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Random rand = new Random();

        return user.getUserId() + "x" + user.hashCode() + rand.nextInt(999) + rand.nextInt(999) + "xxx" + timeStamp;
    }
}
