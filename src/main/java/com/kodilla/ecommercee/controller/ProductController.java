package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @GetMapping(value = "getProducts")
    public List<GenericEntity> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getProduct")
    public GenericEntity getProduct(Long id) {
        return new GenericEntity("1");
    }

    @PostMapping(value = "createProduct")
    public GenericEntity createProduct() {
        return new GenericEntity("2");
    }

    @PutMapping(value = "updateProduct")
    public GenericEntity updateProduct(GenericEntity genericEntity) {
        return new GenericEntity("3");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(Long id) {

    }

}
