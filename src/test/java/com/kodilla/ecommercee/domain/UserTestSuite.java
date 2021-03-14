package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        //Given
        User user = new User("Andrzej", "anderw@golota.pl", "USA");

        //When
        userRepository.save(user);

        //Then
        Long id = user.getUserId();
        Optional<User> readTask = userRepository.findById(id);
        assertTrue(readTask.isPresent());

        //CleanUp
        //userRepository.deleteById(id);
    }
}
