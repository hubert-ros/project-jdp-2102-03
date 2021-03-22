package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        List<Product> products = productService.getAllProducts();
        return productMapper.mapToProductDtoList(products);
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProductById(@RequestParam("productId") Long productId) {
        return productMapper.mapToProductDto(productService.findProductById(productId));
    }

    @GetMapping(value = "getProductName")
    public ProductDto getProductByName(@RequestParam("productName") String productName) {
        Product product = productService.findProductByName(productName);
        return productMapper.mapToProductDto(product);
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        productService.saveProduct(product);
    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.findProductById(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setPrice(productDto.getPrice());
        productService.saveProduct(product);
        return productMapper.mapToProductDto(product);
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam("productId") Long productId) {
        productService.deleteProductById(productId);
    }
}
