package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.ResourceNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cart")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    @PostMapping(value = "createCart")
    public CartDto createCart() {
        Cart cart = cartService.createEmptyCart();
        return cartMapper.mapToCartDTO(cart);
    }

   @GetMapping(value = "getCart")
    public CartDto getCart(@RequestParam long cartId) throws ResourceNotFoundException {
         Cart cart = cartService.getCart(cartId);
         return cartMapper.mapToCartDTO(cart);
    }

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProductsFromCart (@RequestParam long cartId) throws ResourceNotFoundException {
        List<Product> products = cartService.getProductsFromCart(cartId);
        return productMapper.mapToProductDtoList(products);
    }

    @PutMapping(value = "addProduct")
    public List<ProductDto> addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws ResourceNotFoundException {
        List<Product> products = cartService.addProductToCart(cartId, productId);
        return productMapper.mapToProductDtoList(products);
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws ResourceNotFoundException {
        cartService.removeProductFromCart(cartId, productId);
    }

    @DeleteMapping(value = "deleteCart")
    public void deleteCart(@RequestParam long cartId) throws ResourceNotFoundException {
        cartService.deleteCart(cartId);
    }
}
