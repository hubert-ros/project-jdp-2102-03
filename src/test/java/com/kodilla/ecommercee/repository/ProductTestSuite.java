package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class ProductTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void productAddingTest() {

        //Given
        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal(199.99));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));
        Product product3 = new Product("Dress", "Posh evening dress", new BigDecimal(799.00));


        //When
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);


        long productId = product1.getProductId();
        long product2Id = product2.getProductId();
        String product1Name = product1.getProductName();

        product3.setProductName("Evening Dress");
        productRepository.save(product3);
        String newProduct3Name = product3.getProductName();

        //Then
        assertEquals(3, productRepository.findAll().size());
        assertEquals("Suit", productRepository.findByProductId(product2Id).getProductName());
        assertNotEquals(0, productId);

        //Clean-up
        productRepository.delete(product1);
        productRepository.delete(product2);
        productRepository.delete(product3);
    }

    @Test
    public void productDeletingTest() {

        //Given
        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal(199.99));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));
        Product product3 = new Product("Dress", "Posh evening dress", new BigDecimal(799.00));


        //When
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productListBeforeDeletion = productRepository.findAll();

        long productId = product1.getProductId();
        String product1Name = product1.getProductName();
        productRepository.deleteById(productId);

        List<Product> productListAfterDeletion = productRepository.findAll();


        //Then
        assertEquals(3, productListBeforeDeletion.size());
        assertEquals(2, productListAfterDeletion.size());

        //Clean-up
        productRepository.delete(product2);
        productRepository.delete(product3);
    }

    @Test
    public void addCartToListTest() {

        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal("199.99"));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));

        product1.getCarts().add(cart1);
        product1.getCarts().add(cart2);
        product2.getCarts().add(cart2);

        cart2.getProducts().add(product1);
        cart2.getProducts().add(product2);

        //When
        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //Then
        assertEquals(2, cartRepository.findAll().size());
        assertFalse(cartRepository.findAll().isEmpty());
    }


}
