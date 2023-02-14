package com.ecommercetest.controller.admin;

import com.ecommercetest.entity.Brand;
import com.ecommercetest.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class BrandAdminController {
    @Autowired
    private BrandServiceImpl brandService;
    @GetMapping("/brands")
    public String showBrands(Model model) {
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("newBrand", new Brand());
        return "admin/brands";
    }

    @PostMapping("/add-brand")
    public String add(@ModelAttribute("newBrand") Brand brand, RedirectAttributes redirectAttributes) {
        try {
            brandService.save(brand);
            redirectAttributes.addFlashAttribute("success","Added Successfully");
        } catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","Add Unsuccessfully");
        }

        return "redirect:/admin/brands";
    }
    @GetMapping("/brands/{id}")
    public String editBrandForm(@PathVariable("id") Long id, Model model){
        Brand brand=brandService.findById(id);
        model.addAttribute("brand",brand);
        return "admin/brand-form";
    }
    @PostMapping("/brands/save")
    public String saveEdit(Brand brand, RedirectAttributes redirectAttributes){
        try {
            brandService.save(brand);
            redirectAttributes.addFlashAttribute("success","Edit Successfully");
        } catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","Edit Unsuccessfully");
        }

        return "redirect:/admin/brands";
    }
}
