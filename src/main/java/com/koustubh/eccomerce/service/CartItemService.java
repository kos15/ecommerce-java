package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.CartItemException;
import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.Cart;
import com.koustubh.eccomerce.model.CartItem;
import com.koustubh.eccomerce.model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) throws CartItemException;

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
