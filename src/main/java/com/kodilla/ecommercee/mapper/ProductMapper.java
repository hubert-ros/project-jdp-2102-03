package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class ProductMapper {
    public Product mapToProduct(final ProductDto productDto) {
        return new Product(productDto.getProductId(),
                productDto.getProductName(),
                productDto.getProductDescription(),
                productDto.getPrice(),
                productDto.getGroupsOfProduct(),
                productDto.getCarts());
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getPrice(),
                product.getGroupsOfProduct(),
                product.getCarts());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
