package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @PostMapping(value = "createNewCart")
    public GenericEntity createNewCart() {
        return new GenericEntity("newCartDto");
    }

    @GetMapping(value = "getProducts")
    public List<GenericEntity> getProductsFromCart (@PathVariable Long cartId) {
        return new ArrayList<>();
    }

    @PutMapping(value = "addProduct")
    public GenericEntity addProductToCart(@PathVariable Long cartId, GenericEntity productDto) {
        return new GenericEntity();
    }

    @DeleteMapping(value = "deleteProduct")
    public GenericEntity deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return new GenericEntity();
    }

    @PostMapping(value = "createOrder")
    public GenericEntity createOrder(GenericEntity cartDto) {
        return new GenericEntity();
    }

    @GetMapping(value = "getCart")
        public GenericEntity getCart(@PathVariable Long cartId) {
        return new GenericEntity();
    }

    @DeleteMapping(value = "deleteCart")
    public void deleteCart(@PathVariable Long cartId) {
    }
}
