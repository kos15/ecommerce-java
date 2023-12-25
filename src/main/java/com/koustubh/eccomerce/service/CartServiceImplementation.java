package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.CartItemException;
import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.model.Cart;
import com.koustubh.eccomerce.model.CartItem;
import com.koustubh.eccomerce.model.Product;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.repository.CartRepository;
import com.koustubh.eccomerce.request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;
    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());
        try {
            CartItem isPresent = cartItemService.isCartItemExist(cart,product, req.getSize(), userId);
            if(isPresent == null){
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setCart(cart);
                cartItem.setQuantity(req.getQuantity());
                cartItem.setUserId(userId);
                cartItem.setPrice(req.getQuantity() * product.getDiscountedPrice());
                cartItem.setSize(req.getSize());
            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
            }
        } catch (CartItemException e) {
            throw new RuntimeException(e);
        }

        return "Item " + product.getTitle() + " added to cart successfully";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0, totalDiscountedPrice = 0, totalItem = 0;
        for(CartItem cartItem :cart.getCartItems()){
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        return cartRepository.save(cart);
    }
}
