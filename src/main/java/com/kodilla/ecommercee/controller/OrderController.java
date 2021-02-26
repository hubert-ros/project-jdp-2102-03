package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<GenericEntity> getOrders() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder() {
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public GenericEntity getOrder(Long id) {
        return new GenericEntity("2");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public GenericEntity updateOrder(GenericEntity genericEntity) {
        return new GenericEntity("3");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(Long orderId) {

    }


}
