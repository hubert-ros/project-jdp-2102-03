package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testOrderSave() {
        //Given
        User user = new User("user1", "test@mail.com", "address1");
        Cart cart = new Cart();
        Order order = new Order(Order.OrderStatus.PAID);

        order.setCart(cart);
        order.setUser(user);
        order.setStatus(Order.OrderStatus.UNPAID);

        user.getOrders().add(order);
        cart.setOrder(order);

        //When
        userRepository.save(user);
        cartRepository.save(cart);
        orderRepository.save(order);
        Long orderId = order.getId();
        Optional<Order> savedOrder = orderRepository.findById(orderId);

        //Then
        Assert.assertTrue(savedOrder.isPresent());
    }

    @Test
    public void testFindAllOrders() {
        //Given
        User user = new User("user1", "test@mail.com", "address1");
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Order order1 = new Order(Order.OrderStatus.UNPAID);
        Order order2 = new Order(Order.OrderStatus.CANCELED);

        order1.setCart(cart1);
        order1.setUser(user);

        order2.setCart(cart2);
        order2.setUser(user);

        user.getOrders().add(order1);
        user.getOrders().add(order2);
        cart1.setOrder(order1);
        cart2.setOrder(order2);

        //When
        userRepository.save(user);
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = orderRepository.findAll();


        //Then
        Assert.assertEquals(2, orders.size());
    }

    @Test
    public void testDeleteOrderById() {
        //Given
        User user = new User("user1", "test@mail.com", "address1");
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Order order1 = new Order(Order.OrderStatus.UNPAID);
        Order order2 = new Order(Order.OrderStatus.CANCELED);

        order1.setCart(cart1);
        order1.setUser(user);

        order2.setCart(cart2);
        order2.setUser(user);

        user.getOrders().add(order1);
        user.getOrders().add(order2);
        cart1.setOrder(order1);
        cart2.setOrder(order2);

        //When
        userRepository.save(user);
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        orderRepository.save(order1);
        orderRepository.save(order2);

        Long order2Id = order2.getId();
        System.out.println(order2Id);
        orderRepository.deleteById(order2Id);

        List<Order> orders = orderRepository.findAll();


        //Then
        Assert.assertEquals(1, orders.size());
    }


}
