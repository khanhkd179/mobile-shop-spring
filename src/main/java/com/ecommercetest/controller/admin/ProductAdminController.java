package com.ecommercetest.controller.admin;

import com.ecommercetest.dto.ProductDto;
import com.ecommercetest.repository.ProductRepository;
import com.ecommercetest.service.BrandService;
import com.ecommercetest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String products(Model model){
        List<ProductDto> productDtoList=productService.findAll();
        model.addAttribute("products",productDtoList);
        return "admin/products";
    }
    @GetMapping("/products/new")
    public String addProduct(Model model){
        model.addAttribute("brands",brandService.findAll());
        model.addAttribute("product",new ProductDto());
        return "admin/product-form";
    }
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") ProductDto productDto, @RequestParam("imageProduct")MultipartFile imageProduct, RedirectAttributes redirectAttributes){
        try {
            productDto.setActivated(true);
            productService.save(productDto,imageProduct);
            redirectAttributes.addFlashAttribute("success", "Add successfully!");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add!");
        }

        return "redirect:/admin/products";
    }
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long id,Model model){
        ProductDto productDto=productService.getById(id);
        model.addAttribute("productDto",productDto);
        model.addAttribute("brands",brandService.findAll());
        return "admin/edit-product";
    }
    @PostMapping("/update-product/{id}")
    public String processUpdate(@PathVariable("id") Long id,
                                @ModelAttribute("productDto") ProductDto productDto,
                                @RequestParam("imageProduct")MultipartFile imageProduct,
                                RedirectAttributes attributes
    ){
        try {
            productService.updateProduct(imageProduct, productDto);
            attributes.addFlashAttribute("success", "Update successfully!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to update!");
        }
        return "redirect:/admin/products";

    }
    @RequestMapping(value = "/products/change-status/{id}", method = {RequestMethod.PUT , RequestMethod.GET})
    public String changeStatus(@PathVariable("id") Long id){
        productService.changeStatus(id);
        return "redirect:/admin/products";

    }

}
