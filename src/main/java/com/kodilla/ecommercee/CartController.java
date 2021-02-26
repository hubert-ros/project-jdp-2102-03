package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

@GetMapping(value = "getCarts")
    public List<CartDto> getCarts() {
    return new ArrayList<>();
}

@GetMapping(value = "getCart")
    public CartDto getCart(Long cartId) {
    return new CartDto();
}

    @DeleteMapping(value = "deleteCart")
    public void deleteCart(Long cartId) {

    }

    @PutMapping(value = "updateCart")
    public CartDto updateCart(CartDto cartDto) {
        return new CartDto();
    }

    @PostMapping(value = "createCart")
    public void createCart(CartDto cartDto) {

    }

}
