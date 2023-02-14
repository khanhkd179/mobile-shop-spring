package com.ecommercetest.controller.admin;

import com.ecommercetest.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping()
    public String showHomePage() {
        return "admin/admin";
    }


}
