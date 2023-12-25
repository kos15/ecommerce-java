package com.koustubh.eccomerce.controller;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.Cart;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.request.AddItemRequest;
import com.koustubh.eccomerce.response.ApiResponse;
import com.koustubh.eccomerce.service.CartService;
import com.koustubh.eccomerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartContorller {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userService.findUserProfileByJWT(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest request,
                                                     @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJWT(jwt);

        String res = cartService.addCartItem(user.getId(), request);
        ApiResponse response = new ApiResponse(res,true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
