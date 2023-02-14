package com.ecommercetest.dto;

import com.ecommercetest.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private int availableQuantity;
    private double price;
    private Brand brand;
    private String image;
    private boolean activated;
}
