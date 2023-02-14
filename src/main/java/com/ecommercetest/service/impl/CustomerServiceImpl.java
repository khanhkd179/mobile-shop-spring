package com.ecommercetest.service.impl;

import com.ecommercetest.entity.Customer;
import com.ecommercetest.repository.CustomerRepository;
import com.ecommercetest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer getCustomerById(Long id){
        return customerRepository.getById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
