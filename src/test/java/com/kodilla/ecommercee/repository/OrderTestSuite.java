package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testOrderSave() {
        //Given
        User user = new User();
        Cart cart = new Cart();
        Order order = new Order(); //1L, cart, user, Order.OrderStatus.PAID);

        order.setCart(cart);
        order.setUser(user);
        order.setStatus(Order.OrderStatus.PAID);

        user.getOrders().add(order);
        cart.setOrder(order);

        //When
        orderRepository.save(order);
        Long orderId = order.getId();
        Optional<Order> savedOrder = orderRepository.findById(orderId);

        //Then
        System.out.println(orderId);
        Assert.assertTrue(savedOrder.isPresent());
    }
}
