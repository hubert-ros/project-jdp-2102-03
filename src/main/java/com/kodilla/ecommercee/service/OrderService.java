package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(final Long id) {
        orderRepository.deleteById(id);
    }

    public Optional<Order> getOrder(final Long id) {
        return orderRepository.findById(id);
    }

    public Optional<Cart> getCart(final Long id) {
        return cartRepository.findById(id);
    }

    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

}
