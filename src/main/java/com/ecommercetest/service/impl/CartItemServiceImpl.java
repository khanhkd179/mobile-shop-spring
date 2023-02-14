package com.ecommercetest.service.impl;

import com.ecommercetest.entity.CartItem;
import com.ecommercetest.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Override
    public List<CartItem> sortedCartItemById(List<CartItem> cartItems) {
        Collections.sort(cartItems, new Comparator<CartItem>() {

            public int compare(CartItem c1, CartItem c2) {
                // compare two instance of `Score` and return `int` as result.
                return (int) (c1.getId()-c2.getId());
            }
        });
        return cartItems;
    }
}
