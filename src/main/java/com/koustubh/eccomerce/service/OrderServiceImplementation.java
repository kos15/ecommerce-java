package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.OrderException;
import com.koustubh.eccomerce.model.Address;
import com.koustubh.eccomerce.model.Order;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartItemService;
    @Autowired
    private ProductService productService;
    @Override
    public Order createOrder(User user, Address shippingAddress) throws OrderException {
        Order order = new Order();
        return null;
    }

    @Override
    public Order findOrderByld(Long orderld) throws OrderException {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userld) {
        return null;
    }

    @Override
    public Order placedOrder(Long orderld) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderld) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderld) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderld) throws OrderException {
        return null;
    }

    @Override
    public Order cancledOrder(Long orderld) throws OrderException {
        return null;
    }
}
