package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart createCart(final Cart cart) {
        return cartRepository.save(cart);
    }
    public Cart getCart(long cartId) {
        Optional<Cart> findCart = cartRepository.findById(cartId);
        return Optional.of(findCart).get().orElseThrow(NoSuchElementException::new);
    }

    public Order getOrder(long cartId) {
        return cartRepository.findByCartId(cartId)
                .map(Cart::getOrder)
                .orElseThrow(RuntimeException::new);
    }

    public List<Product> getProductsFromCart(long cartId) {
        return new ArrayList<>(cartRepository.findByCartId(cartId)
                .map(Cart::getProducts)
                .orElse(Collections.emptyList()));
    }

    public List<Product> addProductToCart(long cartId, Product product) {
        List<Product> products = cartRepository.findByCartId(cartId)
                .map(Cart::getProducts)
                .orElse(Collections.emptyList());

        products.add(product);
        return products;
    }

    public List<Product> removeProductFromCart(long cartId, long productId) { //ToDo
        List<Product> products = cartRepository.findByCartId(cartId)
                .map(Cart::getProducts)
                .orElse(Collections.emptyList());
        Optional<Product> product = products.stream().filter(n -> n.getProductId().equals(productId)).findFirst();
        return null;
    }
}
