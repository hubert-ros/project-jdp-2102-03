package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.exception.ResourceNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void shouldCreateEmptyCart() {
        //Given
        Cart cart = new Cart(new BigDecimal("0"));
        cartRepository.save(cart);
        long cartId = cart.getCartId();

        //When
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart savedCart = optionalCart.orElse(new Cart(new BigDecimal("0")));
        List<Product> products = savedCart.getProducts();

        //Then
        BigDecimal value = new BigDecimal("0");

        assertNotEquals(0, cartId);
        assertEquals(0, products.size());
        assertEquals(value, cart.getValue());
    }

    @Test
    public void shouldAddOneProduct() {
        //Given
        Group group = new Group("Toys");
        Product product = new Product("Teddy Bear", "little plush toy", new BigDecimal("10"));
        group.getProducts().add(product);
        product.getGroupsOfProduct().add(group);

        Cart cart = new Cart(new BigDecimal("0"));
        cart.getProducts().add(product);
        cart.setValue(new BigDecimal("10"));

        cartRepository.save(cart);
        long cartId = cart.getCartId();

        //When
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart savedCart = optionalCart.orElse(new Cart(new BigDecimal("0")));
        List<Product> products = savedCart.getProducts();

        //Then
        assertNotEquals(0, cartId);
        assertEquals(1, products.size());
        assertEquals(product, cart.getProducts().get(0));
    }

    @Test
    public void shouldAddSeveralOfTheSameProductsToCart(){
        //Given
        Group group = new Group("Toys");
        Product product = new Product("blocks", "wooden blocks", new BigDecimal("4.50"));
        group.getProducts().add(product);
        product.getGroupsOfProduct().add(group);

        Cart cart = new Cart();
        cart.getProducts().add(product);
        cart.getProducts().add(product);
        cart.getProducts().add(product);
        cart.getProducts().add(product);
        cart.getProducts().add(product);
        cart.setValue(new BigDecimal("22.50"));

        cartRepository.save(cart);
        long cartId = cart.getCartId();

        //When
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart savedCart = optionalCart.orElse(new Cart(new BigDecimal("0")));
        List<Product> products = savedCart.getProducts();

        //Then
        assertNotEquals(0, cartId);
        assertEquals(5, products.size());
        assertEquals(cart.getProducts().get(0), cart.getProducts().get(3));
        assertEquals("blocks", cart.getProducts().get(0).getProductName());
    }

    @Test
    public void  shouldDifferentiateBetweenProductsWithSameNameDifferentPrice() {
        //Given
        Group group = new Group("Toys");
        Product product1 = new Product("blocks", "wooden blocks", new BigDecimal("4.50"));
        Product product2 = new Product("blocks", "wooden blocks", new BigDecimal("20"));
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        product1.getGroupsOfProduct().add(group);
        product2.getGroupsOfProduct().add(group);

        Cart cart = new Cart();
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cart.setValue(new BigDecimal("24.50"));
        cartRepository.save(cart);
        long cartId = cart.getCartId();

        //When
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart savedCart = optionalCart.orElse(new Cart(new BigDecimal("0")));
        List<Product> products = savedCart.getProducts();

        //Then
        assertNotEquals(0, cartId);
        assertEquals(2, products.size());
    }

    @Test
    public void shouldRemoveAllProductsFromCart() {
        //Given
        Group group = new Group("Toys");
        Product product1 = new Product("blocks", "wooden blocks", new BigDecimal("4.50"));
        Product product2 = new Product("blocks", "wooden blocks", new BigDecimal("20"));
        Product product3 = new Product("Teddy Bear", "little plush toy", new BigDecimal("10"));

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);

        product1.getGroupsOfProduct().add(group);
        product2.getGroupsOfProduct().add(group);
        product3.getGroupsOfProduct().add(group);

        Cart cart = new Cart();
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cart.getProducts().add(product3);
        cart.setValue(new BigDecimal("34.50"));
        List<Product> products = cart.getProducts();
        cartRepository.save(cart);
        long cartId = cart.getCartId();

        //When
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart savedCart = optionalCart.orElseGet(() -> new Cart(new BigDecimal("0")));
        List<Product> productsInDatabase = savedCart.getProducts();
        productsInDatabase.removeAll(products);
        cart.setValue(new BigDecimal("0"));

        //Then
        BigDecimal value = new BigDecimal("0");

        assertNotEquals(0, cartId);
        assertTrue(products.isEmpty());
        assertEquals(0, productsInDatabase.size());
        assertEquals(value, cart.getValue());
    }

    @Test
    public void shouldRemoveOneProductFromCart() throws ResourceNotFoundException {
        //Given
        Group group = new Group("Toys");
        Product product = new Product("blocks", "wooden blocks", new BigDecimal("4.50"));
        Product product1 = new Product("blocks", "wooden blocks", new BigDecimal("10"));
        Product product2 = new Product("blocks", "wooden blocks", new BigDecimal("20"));
        group.getProducts().add(product);
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        product.getGroupsOfProduct().add(group);
        product1.getGroupsOfProduct().add(group);
        product2.getGroupsOfProduct().add(group);

        Cart cart = new Cart();
        cart.getProducts().add(product);
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cart.setValue(new BigDecimal("34.50"));

        cartRepository.save(cart);
        long cartId = cart.getCartId();

        //When
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart savedCart = optionalCart.orElseThrow(() -> new ResourceNotFoundException("The cart does not exist in the database."));
        List<Product> products = savedCart.getProducts();
        products.remove(product1);
        int cartSize = products.size();

        //Then
        assertNotEquals(0, cartId);
        assertEquals(2, cartSize);
    }
}
