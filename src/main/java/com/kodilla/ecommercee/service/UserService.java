package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public User blockUser(final Long id) {
        User user = userRepository.findById(id).get();
        user.setBlocked(true);
        return userRepository.save(user);
    }

    public String createUserKey(final Long id) {
        String userKey = "RANDOM USER KEY OF USER " + id;

        return userKey;
    }
}
