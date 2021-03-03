package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @GetMapping(value = "getOrders")
    public List<GenericEntity> getOrders() {
        return new ArrayList<>();
    }

    @PostMapping(value = "createOrder")
    public void createOrder(GenericEntity genericEntity) {
    }

    @GetMapping(value = "getOrder")
    public GenericEntity getOrder(Long id) {
        return new GenericEntity("2");
    }

    @PutMapping(value = "updateOrder")
    public GenericEntity updateOrder(GenericEntity genericEntity) {
        return new GenericEntity("3");
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(Long orderId) {

    }
}
