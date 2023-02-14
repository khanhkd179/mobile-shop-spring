package com.ecommercetest.service;

import com.ecommercetest.entity.Customer;

public interface CustomerService {
    Customer getCustomerById(Long id);

    void save(Customer customer);
}
