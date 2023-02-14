package com.ecommercetest.service.impl;

import com.ecommercetest.entity.Brand;
import com.ecommercetest.repository.BrandRepository;
import com.ecommercetest.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public Brand update(Brand brand) {
        return null;
    }
}
