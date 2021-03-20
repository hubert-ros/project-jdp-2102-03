package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;
    @Autowired
    private final OrderMapper orderMapper;

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orderMapper.mapToOrderDtoList(orders);
    }

    @PostMapping(value = "createOrder")
    public OrderDto createOrder(long cardId, long userId) throws CartNotFoundException, UserNotFoundException {
        Cart cart = orderService.getCart(cardId).orElseThrow(CartNotFoundException::new);
        User user = orderService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Order order = new Order(Order.OrderStatus.UNPAID);
        order.setCart(cart);
        order.setUser(user);
        Order savedOrder = orderService.saveOrder(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    @GetMapping(value = "getOrder")
    public OrderDto getOrder(Long id) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(
                orderService.getOrder(id).orElseThrow(OrderNotFoundException::new));
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderService.saveOrder(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(Long orderId) throws OrderNotFoundException {
        orderService.getOrder(orderId).orElseThrow(OrderNotFoundException::new);
        orderService.deleteOrder(orderId);
    }
}
