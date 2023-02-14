package com.ecommercetest.controller;

import com.ecommercetest.entity.Customer;
import com.ecommercetest.service.BrandService;
import com.ecommercetest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BrandService brandService;
    @GetMapping("/profile")
    public String showProfile(Model model){
        Customer customer=customerService.getCustomerById(2L);
        model.addAttribute("customer",customer);
        model.addAttribute("brands",brandService.findAll());
        return "profile";
    }
    @PostMapping("/update-profile")
    public String updateProfile(Model model,Customer customer){

        customerService.save(customer);
        return "redirect:/profile";
    }
}
