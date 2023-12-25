package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.model.Cart;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.request.AddItemRequest;

public interface CartService {
    public Cart createCart (User user);
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
    public Cart findUserCart(Long userId);
}
