package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.CartItemException;
import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.Cart;
import com.koustubh.eccomerce.model.CartItem;
import com.koustubh.eccomerce.model.Product;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.repository.CartItemRepository;
import com.koustubh.eccomerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item= findCartItemById(id);
        User user = userService.findUserById(item.getUserId());
        if(user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) throws CartItemException {
        return cartItemRepository.isCartitemExist(cart,product,size,userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        User requestedUser = userService.findUserById(userId);

        if(user.getId().equals(requestedUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }
        throw new CartItemException("You cann't remove another user's cart..!");
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> cartItem = cartItemRepository.findById((cartItemId));
        if(cartItem.isPresent()){
            return cartItem.get();
        }
        throw new CartItemException("No CartItem found for ID - " + cartItemId);
    }
}
