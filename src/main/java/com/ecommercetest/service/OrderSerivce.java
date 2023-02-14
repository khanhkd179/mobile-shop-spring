package com.ecommercetest.service;

import com.ecommercetest.entity.Order;
import com.ecommercetest.entity.ShoppingCart;

import java.util.List;

public interface OrderSerivce {
    void saveOrder(ShoppingCart cart);
    List<Order> getListOrder();

}
