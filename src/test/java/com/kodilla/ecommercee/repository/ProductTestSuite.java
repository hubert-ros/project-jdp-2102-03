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
    public void productAddingTest() {

        //Given
        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal(199.99));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));
        Product product3 = new Product("Dress", "Posh evening dress", new BigDecimal(799.00));


        //When
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productList = (List<Product>) productRepository.findAll();

        long productId = product1.getProductId();
        String product1Name = product1.getProductName();

        product3.setProductName("Evening Dress");
        productRepository.save(product3);
        String newProduct3Name = product3.getProductName();

        //Then
        assertEquals(3, productList.size());
        assertEquals("Suit", productList.get(1).getProductName());
        assertEquals("Shoes", product1Name);
        assertEquals("Evening Dress", newProduct3Name);
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

        List<Product> productListBeforeDeletion = (List<Product>) productRepository.findAll();

        long productId = product1.getProductId();
        String product1Name = product1.getProductName();
        productRepository.deleteById(productId);

        List<Product> productListAfterDeletion = (List<Product>) productRepository.findAll();


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

        List<Cart> carts = product1.getCarts();

        //When
        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart2);
        long productId = product1.getProductId();

        //Then
        assertNotEquals(0, productId); //spr. czy produkt dodano do bazy, baza jest numerowana od 1 więc nierówne
        assertEquals(2, carts.size());
        assertEquals("Suit", product2.getProductName());
        assertEquals(cart1, product1.getCarts().get(0)); //utworzony obiekt i koszyk na liście to te same koszyki
    }

    @Test
    public void addGroupToSetTest() {
        //Given
        Set<Group> groupsOfProducts = new HashSet<>();
        Group groupSport = new Group();
        Group groupClothes = new Group();
        groupsOfProducts.add(groupSport);
        groupsOfProducts.add(groupClothes);

        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal("199.99"));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));

        product1.getGroupsOfProduct().add(groupSport);
        product1.getGroupsOfProduct().add(groupClothes);

        //When
        productRepository.save(product1);
        long productId = product1.getProductId();

        //Then
        assertNotEquals(0, productId);
        assertEquals(2, groupsOfProducts.size());
        assertTrue(groupsOfProducts.contains(groupSport));
    }

    @Test
    public void addProductToCartTest() {

        //Given
        Cart cart1 = new Cart();

        Product product1 = new Product("Shoes", "Super comfortable running shoes", new BigDecimal("199.99"));
        Product product2 = new Product("Suit", "100% wool business suit", new BigDecimal(1199.00));


        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);



        //When
        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart1);

        //Then
        assertEquals(2, cart1.getProducts().size());
    }

}
