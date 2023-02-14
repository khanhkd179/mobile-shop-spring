package com.ecommercetest.service;

import com.ecommercetest.dto.ProductDto;
import com.ecommercetest.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    Product findById(Long id);

    Product save(ProductDto productDto, MultipartFile imageProduct);

    List<Product> findAllByBrandName(String name);

    ProductDto getById(Long id);
    Product updateProduct(MultipartFile imageProduct,ProductDto productDto);
    Product changeStatus(Long id);

}
