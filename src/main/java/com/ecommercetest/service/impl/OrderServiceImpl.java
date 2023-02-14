package com.ecommercetest.service.impl;

import com.ecommercetest.entity.*;
import com.ecommercetest.repository.CartItemRepository;
import com.ecommercetest.repository.OrderDetailRepository;
import com.ecommercetest.repository.OrderRepository;
import com.ecommercetest.repository.ShoppingCartRepository;
import com.ecommercetest.service.OrderSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderSerivce {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ShoppingCartRepository cartRepository;
    @Override
    public void saveOrder(ShoppingCart cart) {
        Order order=new Order();
        order.setOrderStatus("PENDING");
        order.setOrderDate(new Date());
        Customer customer=cart.getCustomer();
        order.setCustomer(customer);
        order.setTotalPrice(cart.getTotalPrices());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        for(CartItem item:cart.getCartItems()){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProduct(item.getProduct());
            orderDetail.setUnitPrice(item.getProduct().getPrice());
            orderDetailList.add(orderDetail);
            orderDetailRepository.save(orderDetail);

        }
        order.setOrderDetailList(orderDetailList);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getListOrder() {
        return orderRepository.findAll();
    }
}
