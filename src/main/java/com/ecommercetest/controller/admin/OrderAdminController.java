package com.ecommercetest.controller.admin;

import com.ecommercetest.service.OrderSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderAdminController {
    @Autowired
    private OrderSerivce orderSerivce;
    @GetMapping("/admin/orders")
    public String getOrderList(Model model){
        model.addAttribute("orders",orderSerivce.getListOrder());
        return "admin/orders";
    }
}
