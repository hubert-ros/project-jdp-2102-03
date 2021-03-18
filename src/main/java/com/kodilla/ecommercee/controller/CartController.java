package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {
    private final CartMapper cartMapper;
    private final CartService cartService;
    private final ProductMapper productMapper;

    @Autowired
    public CartController(CartMapper cartMapper, CartService cartService, ProductMapper productMapper) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
        this.productMapper = productMapper;
    }

    @PostMapping(value = "createCart", consumes = MediaType.APPLICATION_JSON_VALUE) //ToDo status 200 OK
    public CartDto createCart(@RequestBody CartDto cartDto) {
        Cart createdCart = cartMapper.mapToCart(cartDto);
        return cartMapper.mapToCartDTO(cartService.createCart(createdCart));
    }

   @GetMapping(value = "getCart") // ToDo status 200 OK
    public CartDto getCart(@RequestParam long cartId) {
         Cart cart = cartService.getCart(cartId);
         return cartMapper.mapToCartDTO(cart);
    }

    @GetMapping(value = "getProducts") //ToDo status 200 OK
    public List<ProductDto> getProductsFromCart (@RequestParam long cartId) {
        List<Product> products = cartService.getProductsFromCart(cartId);
        return productMapper.mapToProductDtoList(products);
    }

    @PutMapping(value = "addProduct", consumes = MediaType.APPLICATION_JSON_VALUE) // ToDo status 500
    public List<ProductDto> addProductToCart(@RequestParam Long cartId, @RequestBody ProductDto productDto) {
        Product addedProduct = productMapper.mapToProduct(productDto);
        cartService.addProductToCart(cartId, addedProduct);
        return getProductsFromCart(cartId);
    }

    @DeleteMapping(value = "deleteProduct") // ToDo
    public GenericEntity deleteProductFromCart(@RequestParam long cartId, long productId) {
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
