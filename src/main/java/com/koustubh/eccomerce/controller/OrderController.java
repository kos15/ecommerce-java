package com.koustubh.eccomerce.controller;

import com.koustubh.eccomerce.exception.OrderException;
import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.Address;
import com.koustubh.eccomerce.model.Order;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.service.OrderService;
import com.koustubh.eccomerce.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        User user = userService.findUserProfileByJWT(jwt);
        Order order = orderService.createOrder(user, shippingAddress);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        User user = userService.findUserProfileByJWT(jwt);
        List<Order> order = orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<List<Order>>(order, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("Id") Long orderId,@RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        User user = userService.findUserProfileByJWT(jwt);
        Order order = orderService.findOrderByld(orderId);

        return new ResponseEntity<Order>(order, HttpStatus.ACCEPTED);
    }
}
