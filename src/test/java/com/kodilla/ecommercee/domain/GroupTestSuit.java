package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupTestSuit {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateGroup() {
        //Given
        Group groupOne = new Group();

        //When
        groupRepository.save(groupOne);
        long id = groupOne.getGroupId();
        Optional<Group> createGroup = groupRepository.findById(id);

        //Then
        assertTrue(createGroup.isPresent());

        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testGetAllGroup() {
        //Given
        Group groupOne = new Group();
        Group groupTwo = new Group();
        Group grouThree = new Group();
        Group groupFour = new Group();

        //When
        groupRepository.save(groupOne);
        groupRepository.save(groupTwo);
        groupRepository.save(grouThree);
        groupRepository.save(groupFour);
        long idGroupOne = groupOne.getGroupId();
        long idGroupTwo = groupTwo.getGroupId();
        long idGroupThree = grouThree.getGroupId();
        long idGroupFour = groupFour.getGroupId();

        List<Group> findAllGroups = groupRepository.findAll();

        //Then
        assertEquals(4, findAllGroups.size());

        //CleanUp
        groupRepository.deleteById(idGroupOne);
        groupRepository.deleteById(idGroupTwo);
        groupRepository.deleteById(idGroupThree);
        groupRepository.deleteById(idGroupFour);
    }

    @Test
    public void testGetGroupById() {
        //Given
        Group groupOne = new Group();

        //When
        groupRepository.save(groupOne);
        long id = groupOne.getGroupId();
        Optional<Group> findOneGroup = groupRepository.findById(id);

        //Then
        assertTrue(findOneGroup.isPresent());

        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testDeleteGroupById() {
        //Given
        Group groupOne = new Group();
        Group groupTwo = new Group();

        //When
        groupRepository.save(groupOne);
        groupRepository.save(groupTwo);
        long idGroupOne = groupOne.getGroupId();
        long idGroupTwo = groupTwo.getGroupId();
        groupRepository.deleteById(idGroupOne);

        //Then
        assertEquals(1, groupRepository.findAll().size());

        //CleanUp
        groupRepository.deleteById(idGroupTwo);
    }

    @Test
    public void testUpdateGroup() {
        //Given
        Set<Product> listGroupOne = new HashSet<>();
        Group groupOne = new Group("GroupOneTest", listGroupOne);

        //When
        groupRepository.save(groupOne);
        long id = groupOne.getGroupId();
        groupOne.setName("UpdateGroupOneTest");

        //Then
        assertEquals("UpdateGroupOneTest", groupOne.getName());

        //CleanUp
        groupRepository.deleteById(id);
    }
}