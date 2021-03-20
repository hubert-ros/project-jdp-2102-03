package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.ResourceNotExistException;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart createEmptyCart() {
        Cart cart = new Cart(new BigDecimal("0"));
        return cartRepository.save(cart);
    }
    public Cart getCart(long cartId) throws ResourceNotExistException {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        return optionalCart.orElseThrow(() -> new ResourceNotExistException("Cart not exists"));
    }

    public List<Product> getProductsFromCart(long cartId) throws ResourceNotExistException {
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        Cart findCart = optionalCart.orElseThrow(() -> new ResourceNotExistException("Cart not exists"));
        return findCart.getProducts();
    }

    public List<Product> addProductToCart(long cartId, Product product) throws ResourceNotExistException {
        Optional<Cart> optionalCart =  cartRepository.findByCartId(cartId);
        Cart findCart = optionalCart.orElseThrow(() -> new ResourceNotExistException("Cart not exists"));

        List<Product> products = findCart.getProducts();
        products.add(product);

        BigDecimal cartValue = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        findCart.setValue(cartValue);

        cartRepository.save(findCart);
        return products;
    }

    public List<Product> removeProductFromCart(long cartId, long productId) throws ResourceNotExistException {
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        Cart findCart = optionalCart.orElseThrow(() -> new ResourceNotExistException("Cart not exists"));
        List<Product> products = findCart.getProducts();

        Optional<Product> optionalProduct = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst();
        Product productToRemove = optionalProduct.orElseThrow(() -> new ResourceNotExistException("No such product in your shopping cart"));
        products.remove(productToRemove);

        return products;
    }

    public void deleteCart(long cartId) throws ResourceNotExistException {
        Optional<Cart> optionalCart = cartRepository.findByCartId(cartId);
        Cart findCart = optionalCart.orElseThrow(() -> new ResourceNotExistException("Cart not exists"));
        cartRepository.deleteById(findCart.getCartId());
    }
}
