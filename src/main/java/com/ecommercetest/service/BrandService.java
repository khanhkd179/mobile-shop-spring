package com.ecommercetest.service;

import com.ecommercetest.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();
    Brand save(Brand brand);
    Brand findById(Long id);
    Brand update(Brand brand);

}
