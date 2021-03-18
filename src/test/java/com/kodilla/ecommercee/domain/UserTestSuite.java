package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;


    @Test
    public void testCreateUser() {
        //Given
        User user = new User("Andrzej", "anderw@golota.pl", "USA");
        userRepository.save(user);

        //When

        //Then
        Long id = user.getUserId();

        assertTrue(userRepository.findById(id).isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testFindByUserName() {
         //Given
         User user = new User("Andrzej", "anderw@golota.pl", "USA");
         userRepository.save(user);

         //When

         //Then
         assertTrue(userRepository.findByUserName("Andrzej").isPresent());

         //CleanUp
         userRepository.deleteById(user.getUserId());
    }

    @Test
    public void testUserUpdate() {
        //Given
        User user = new User("Andrzej", "anderw@golota.pl", "USA");
        userRepository.save(user);

        //When
        Long id = user.getUserId();
        user.setEMail("xxx@xxx.pl");
        userRepository.save(user);

        //Then
        assertEquals("xxx@xxx.pl", userRepository.findById(id).get().getEMail());
        assertEquals(1, userRepository.count());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testFindAll() {
        //Given
        User user1 = new User("Andrzej", "anderw@golota.pl", "USA");
        User user2 = new User("Tomek", "qwewqe@wewqwqe.pl", "PL");
        User user3 = new User("Ola", "wqewqe@wewqewq.pl", "DE");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //When
        List<User> users = userRepository.findAll();

        //Then
        assertEquals(3, users.size());

        //CleanUp
        userRepository.deleteById(user1.getUserId());
        userRepository.deleteById(user2.getUserId());
        userRepository.deleteById(user3.getUserId());
    }

    @Test
    public void testBlockUser() {
        //Given
        User user = new User("Pan kleks", "a@b.pl", "x");
        userRepository.save(user);

        //When
        Long id = user.getUserId();

        User readUser = userRepository.findById(id).get();
        readUser.setBlocked(true);
        userRepository.save(readUser);

        //Then
        assertTrue(userRepository.findById(id).get().isBlocked());
        assertEquals(1, userRepository.count());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void shouldCreateRelationWithOrder() {
        //Given
        User user = new User("user 1", "user@mail.pl", "PL");
        Order order = new Order(Order.OrderStatus.UNPAID);
        userRepository.save(user);
        orderRepository.save(order);

        user.getOrders().add(order);
        order.setUser(user);
        userRepository.save(user);
        orderRepository.save(order);

        //When
        Long orderId = order.getId();
        Order readOrder = orderRepository.findById(orderId).get();

        //Then
        assertEquals("user 1", readOrder.getUser().getUserName());
    }
}