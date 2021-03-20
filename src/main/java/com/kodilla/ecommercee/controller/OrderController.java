package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
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
    public OrderDto createOrder(@RequestParam long cardId, long userId) throws CartNotFoundException, UserNotFoundException {
        Cart cart = orderService.getCart(cardId).orElseThrow(CartNotFoundException::new);
        User user = orderService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Order createdOrder = orderService.createOrder(cart, user);
        return orderMapper.mapToOrderDto(createdOrder);
    }

    @GetMapping(value = "getOrder")
    public OrderDto getOrder(@RequestParam Long id) throws OrderNotFoundException {
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
    public void deleteOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        orderService.getOrder(orderId).orElseThrow(OrderNotFoundException::new);
        orderService.deleteOrder(orderId);
    }
}
