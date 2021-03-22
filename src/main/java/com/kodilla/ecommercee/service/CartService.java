package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.ResourceNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository repository;

    public BigDecimal calculateValueOfProducts(List<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Cart createEmptyCart() {
        Cart cart = new Cart(new BigDecimal("0"));
        return cartRepository.save(cart);
    }
    public Cart getCart(long cartId) throws ResourceNotFoundException {
        return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not exists"));
    }

    public List<Product> getProductsFromCart(long cartId) throws ResourceNotFoundException {
        return getCart(cartId).getProducts();
    }

    public List<Product> addProductToCart(long cartId, long productId) throws ResourceNotFoundException {
        Product findProduct = repository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not exists"));

        Cart findCart = getCart(cartId);
        List<Product> products = findCart.getProducts();
        products.add(findProduct);

        BigDecimal cartValue = calculateValueOfProducts(products);
        findCart.setValue(cartValue);
        cartRepository.save(findCart);

        return products;
    }

    public void removeProductFromCart(long cartId, long productId) throws ResourceNotFoundException {
        Cart findCart = getCart(cartId);
        List<Product> products = findCart.getProducts();

        Optional<Product> optionalProduct = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findAny();
        Product productToRemove = optionalProduct.orElseThrow(() -> new ResourceNotFoundException("No such product in your shopping cart"));
        products.remove(productToRemove);

        BigDecimal cartValue = calculateValueOfProducts(products);
        findCart.setValue(cartValue);
        cartRepository.save(findCart);

    }

    public void deleteCart(long cartId) throws ResourceNotFoundException {
        Cart findCart = getCart(cartId);
        cartRepository.deleteById(findCart.getCartId());
    }
}
