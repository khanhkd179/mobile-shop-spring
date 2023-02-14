package com.ecommercetest.controller;

import com.ecommercetest.entity.Customer;
import com.ecommercetest.entity.Order;
import com.ecommercetest.entity.ShoppingCart;
import com.ecommercetest.repository.CustomerRepository;
import com.ecommercetest.service.BrandService;
import com.ecommercetest.service.CustomerService;
import com.ecommercetest.service.OrderSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OderController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderSerivce orderSerivce;
    @Autowired
    private BrandService brandService;
    @GetMapping("/check-out")
    public String checkOutForm(Model model){
        Customer customer=customerService.getCustomerById(2L);
        model.addAttribute("customer",customer);
        model.addAttribute("cart",customer.getShoppingCart());
        return "check-out";
    }

    @GetMapping("/orders")
    public String showOrder(Model model){
        Customer customer=customerService.getCustomerById(2L);
        List<Order> orders=customer.getOrders();
        model.addAttribute("orders",orders);
        model.addAttribute("brands",brandService.findAll());
        return "order";
    }
    @GetMapping("/place-order")
    public String placeOrder(){
        Customer customer=customerService.getCustomerById(2L);
        ShoppingCart cart=customer.getShoppingCart();
        orderSerivce.saveOrder(cart);

        return "redirect:/orders";
    }
}
