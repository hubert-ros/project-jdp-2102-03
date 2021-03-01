package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<GenericEntity> getProducts() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public GenericEntity getProduct(@PathVariable Long id) {
        return new GenericEntity("1");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct")
    public GenericEntity createProduct() {
        return new GenericEntity("2");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public GenericEntity updateProduct(GenericEntity genericEntity) {
        return new GenericEntity("3");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@PathVariable Long id) {

    }

}
