package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal price;

    public ProductDto(String productName, String productDescription, BigDecimal price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }
}
