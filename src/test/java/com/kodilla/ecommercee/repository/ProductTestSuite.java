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
    public void addCartToListTest() {

        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal("199.99"));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));

        product1.getCarts().add(cart1);
        product1.getCarts().add(cart2);


        List<Cart> carts = product1.getCarts();


        //When
        productRepository.save(product1);
        //cartRepository.save(cart1);
        //cartRepository.save(cart2);
        //Optional<Product> savedProduct = Optional.ofNullable(productRepository.save(product1));
        long productId = product1.getProductId();


        //Then
        assertNotEquals(0, productId); //spr. czy produkt dodano do bazy, baza jest numerowana od 1 więc nierówne
        assertEquals(2, carts.size());
        assertEquals(cart1, product1.getCarts().get(0)); //utworzony obiekt i koszyk na liście to te same koszyki
    }

    /*@Test
    public void addGroupToSetTest() {
        //Given
        Set<Product> listGroupOne = new HashSet<>();
        Group group1 = new Group();
        Group group2 = new Group();

        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal("199.99"));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));

        product1.getGroupsOfProduct().add(group1);
        product1.getGroupsOfProduct().add(group2);

        Set<Group> groupsOfProducts = new HashSet<>();


        //When
        productRepository.save(product1);
        //cartRepository.save(cart1);
        //cartRepository.save(cart2);
        Optional<Product> savedProduct = Optional.ofNullable(productRepository.save(product1));
        long productId = product1.getProductId();


        //Then
        assertNotEquals(0, productId); //spr. czy produkt dodano do bazy, baza jest numerowana od 1 więc nierówne
        assertEquals(2, groupsOfProducts.size());
        //assertEquals(group1, product1.getGroupsOfProduct().get); //utworzony obiekt i koszyk na liście to te same koszyki


    }
*/

}
