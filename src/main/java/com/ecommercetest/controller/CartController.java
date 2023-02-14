package com.ecommercetest.controller;

import com.ecommercetest.entity.CartItem;
import com.ecommercetest.entity.Customer;
import com.ecommercetest.entity.Product;
import com.ecommercetest.entity.ShoppingCart;
import com.ecommercetest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private BrandService brandService;

    @GetMapping("/cart")
    public String showCart(Model model) {

        Customer customer = customerService.getCustomerById(2L);

        ShoppingCart shoppingCart = customer.getShoppingCart();
        List<CartItem> cartItems = cartItemService.sortedCartItemById(shoppingCart.getCartItems());
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("brands",brandService.findAll());
        return "cart";
    }

    @RequestMapping(value = "/add-to-cart/{id}", method = {RequestMethod.POST, RequestMethod.GET})
//    @GetMapping("/add-to-cart/{id}")
    public String addItemToCart(@PathVariable("id") Long productId, Model model) {
        Product product = productService.findById(productId);
        Customer customer = customerService.getCustomerById(2L);
        ShoppingCart cart = shoppingCartService.addItemToCart(product, 1, customer);
        model.addAttribute("shoppingCart", cart);
        return "redirect:/cart";
    }
    @RequestMapping(value = "/decrease-quantity/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
//    @GetMapping("/decrease-quantity/{id}")
    public String decreaseQuantity(@PathVariable("id") Long productId, Model model) {
        Product product = productService.findById(productId);
        Customer customer = customerService.getCustomerById(2L);
        ShoppingCart cart = shoppingCartService.decreaseQuantityCartItem(product, customer);
        model.addAttribute("shoppingCart", cart);
        return "redirect:/cart";
    }
    @RequestMapping(value = "/remove-cart-item/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
//    @GetMapping("/remove-cart-item/{id}")
    public String removeItem(@PathVariable("id") Long productId, Model model) {
        Product product = productService.findById(productId);
        Customer customer = customerService.getCustomerById(2L);
        ShoppingCart cart = shoppingCartService.removeCartItem(product, customer);
        model.addAttribute("shoppingCart", cart);
        return "redirect:/cart";
    }

}
