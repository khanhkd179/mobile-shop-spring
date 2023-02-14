package com.ecommercetest.service;

import com.ecommercetest.entity.Customer;
import com.ecommercetest.entity.Product;
import com.ecommercetest.entity.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Product product, int quantity, Customer customer);

    ShoppingCart decreaseQuantityCartItem(Product product, Customer customer);

    ShoppingCart removeCartItem(Product product, Customer customer);
}
