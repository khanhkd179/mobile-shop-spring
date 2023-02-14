package com.ecommercetest.controller;

import com.ecommercetest.dto.ProductDto;
import com.ecommercetest.entity.Customer;
import com.ecommercetest.entity.Product;
import com.ecommercetest.repository.CustomerRepository;
import com.ecommercetest.service.BrandService;
import com.ecommercetest.service.CustomerService;
import com.ecommercetest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
@Autowired
private BrandService brandService;
    @GetMapping("/")
    public String showHomePage(Model model){
        List<ProductDto> products=productService.findAll();
        List<ProductDto> listProducts=new ArrayList<>();
        //get enabled product
        for(ProductDto product : products){
            if(product.isActivated()) listProducts.add(product);
        }
        model.addAttribute("listProducts",listProducts);
        model.addAttribute("brands",brandService.findAll());
        return "index";
    }
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

}
