package com.ecommercetest.controller;

import com.ecommercetest.dto.ProductDto;
import com.ecommercetest.entity.Brand;
import com.ecommercetest.entity.Product;
import com.ecommercetest.service.BrandService;
import com.ecommercetest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Long id , Model model){
        Product product=productService.findById(id);
        model.addAttribute("brands",brandService.findAll());
        model.addAttribute(product);
        return "product-detail";
    }
    @GetMapping("/collection/{name}")
    public String showProductByCategory(Model model,@PathVariable("name")String name){
        List<ProductDto> products=productService.findAll();
        List<ProductDto> listProducts=new ArrayList<>();
        //get enabled product
        for(ProductDto product : products){
            if(product.isActivated() && product.getBrand().getName().equalsIgnoreCase(name))
                listProducts.add(product);
        }
        model.addAttribute("listProducts",listProducts);
        model.addAttribute("brands",brandService.findAll());
        return "index";
    }

}
