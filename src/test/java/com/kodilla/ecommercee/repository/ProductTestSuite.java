package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class ProductTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;


    @Test
    public void testProductAddedToCart() {

        //Given
        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal("199.99"));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));

        List<Product> products = new ArrayList<>();
        //products.add(product1);
        //products.add(product2);

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cart1.setProducts(products);


        //When
        cartRepository.save(cart1);

        //Then
        assertEquals(1, cartRepository.count());
        //assertEquals(false, products.isEmpty());



    }



}
