package com.ecommercetest.service;

import com.ecommercetest.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> sortedCartItemById(List<CartItem> cartItems);
}
