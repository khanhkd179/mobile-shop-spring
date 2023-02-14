package com.ecommercetest.service.impl;

import com.ecommercetest.entity.CartItem;
import com.ecommercetest.entity.Customer;
import com.ecommercetest.entity.Product;
import com.ecommercetest.entity.ShoppingCart;
import com.ecommercetest.repository.CartItemRepository;
import com.ecommercetest.repository.ShoppingCartRepository;
import com.ecommercetest.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart addItemToCart(Product product, int quantity, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        if (cart == null) {
            cart = new ShoppingCart();
        }
        List<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = findCartItem(cartItems, product.getId());
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setPrice(product.getPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);

            }
        } else {
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setPrice(product.getPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItemRepository.save(cartItem);
            }
        }
        cart.setCartItems(cartItems);
        cart.setTotalItems(totalItems(cart.getCartItems()));
        cart.setTotalPrices(totalPrice(cart.getCartItems()));
        cart.setCustomer(customer);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart decreaseQuantityCartItem(Product product, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        List<CartItem> cartItems=cart.getCartItems();
        CartItem cartItem=findCartItem(cartItems,product.getId());
        cartItem.setQuantity(cartItem.getQuantity()-1);
        cartItemRepository.save(cartItem);
        if(cartItem.getQuantity()==0){
            cartItemRepository.deleteById(cartItem.getId());
            cartItems.remove(cartItem);
        }
        cart.setCartItems(cartItems);
        cart.setTotalItems(totalItems(cart.getCartItems()));
        cart.setTotalPrices(totalPrice(cart.getCartItems()));
        cart.setCustomer(customer);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart removeCartItem(Product product, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        List<CartItem> cartItems=cart.getCartItems();
        CartItem cartItem=findCartItem(cartItems,product.getId());
        cartItems.remove(cartItem);
        cartItemRepository.deleteById(cartItem.getId());
        cart.setCartItems(cartItems);
        cart.setTotalItems(totalItems(cart.getCartItems()));
        cart.setTotalPrices(totalPrice(cart.getCartItems()));
        cart.setCustomer(customer);
        return shoppingCartRepository.save(cart);
    }

    private CartItem findCartItem(List<CartItem> cartItems, Long productId) {
        if (cartItems == null) {
            return null;
        }
        CartItem cartItem = null;

        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productId) {
                cartItem = item;
            }
        }
        return cartItem;
    }
    private int totalItems(List<CartItem> cartItems){
        int totalItems = 0;
        for(CartItem item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }
    private double totalPrice(List<CartItem> cartItems){
        double totalPrice = 0.0;

        for(CartItem item : cartItems){
            totalPrice += item.getQuantity()*item.getPrice();
        }

        return totalPrice;
    }
}
