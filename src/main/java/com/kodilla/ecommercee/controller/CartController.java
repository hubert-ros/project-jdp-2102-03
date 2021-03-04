package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @PostMapping(value = "createCart")
    public GenericEntity createCart() {
        return new GenericEntity("CartDto");
    }

    @GetMapping(value = "getProducts")
    public List<GenericEntity> getProductsFromCart (Long cartId) {
        return new ArrayList<>();
    }

    @PutMapping(value = "addProduct")
    public GenericEntity addProductToCart(Long cartId, GenericEntity productDto) {
        return new GenericEntity();
    }

    @DeleteMapping(value = "deleteProduct")
    public GenericEntity deleteProductFromCart(Long cartId, Long productId) {
        return new GenericEntity();
    }

    @PostMapping(value = "createOrder")
    public GenericEntity createOrder(GenericEntity cartDto) {
        return new GenericEntity();
    }

    @DeleteMapping(value = "deleteCart")
    public void deleteCart(Long cartId) {
    }
}
