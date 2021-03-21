package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.ResourceNotExistException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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
    public Cart getCart(long cartId) throws ResourceNotExistException {
        return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotExistException("Cart not exists"));
    }

    public List<Product> getProductsFromCart(long cartId) throws ResourceNotExistException {
        return getCart(cartId).getProducts();
    }

    public List<Product> addProductToCart(long cartId, long productId) throws ResourceNotExistException {
        Product findProduct = repository.findById(productId).orElseThrow(() -> new ResourceNotExistException("Product not exists"));

        Cart findCart = getCart(cartId);
        List<Product> products = findCart.getProducts();
        products.add(findProduct);

        BigDecimal cartValue = calculateValueOfProducts(products);
        findCart.setValue(cartValue);
        cartRepository.save(findCart);

        return products;
    }

    public List<Product> removeProductFromCart(long cartId, long productId) throws ResourceNotExistException {
        Cart findCart = getCart(cartId);
        List<Product> products = findCart.getProducts();

        Optional<Product> optionalProduct = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findAny();
        Product productToRemove = optionalProduct.orElseThrow(() -> new ResourceNotExistException("No such product in your shopping cart"));
        products.remove(productToRemove);

        BigDecimal cartValue = calculateValueOfProducts(products);
        findCart.setValue(cartValue);
        cartRepository.save(findCart);

        return products;
    }

    public void deleteCart(long cartId) throws ResourceNotExistException {
        Cart findCart = getCart(cartId);
        cartRepository.deleteById(findCart.getCartId());
    }
}
