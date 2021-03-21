package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.ResourceNotExistException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public CartDto getCart(@RequestParam long cartId) throws ResourceNotExistException {
         Cart cart = cartService.getCart(cartId);
         return cartMapper.mapToCartDTO(cart);
    }

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProductsFromCart (@RequestParam long cartId) throws ResourceNotExistException {
        List<Product> products = cartService.getProductsFromCart(cartId);
        return productMapper.mapToProductDtoList(products);
    }

    @PutMapping(value = "addProduct")
    public List<ProductDto> addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws ResourceNotExistException{
        List<Product> products = cartService.addProductToCart(cartId, productId);
        return productMapper.mapToProductDtoList(products);
    }

    @DeleteMapping(value = "deleteProduct")
    public List<ProductDto> deleteProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws ResourceNotExistException {
        List<Product> products = cartService.removeProductFromCart(cartId, productId);
        return productMapper.mapToProductDtoList(products);
    }

    @DeleteMapping(value = "deleteCart")
    public void deleteCart(@RequestParam long cartId) throws ResourceNotExistException {
        cartService.deleteCart(cartId);
    }
}
