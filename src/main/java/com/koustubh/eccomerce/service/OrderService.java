package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.OrderException;
import com.koustubh.eccomerce.model.Address;
import com.koustubh.eccomerce.model.Order;
import com.koustubh.eccomerce.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address shippingAddress) throws OrderException;
    public Order findOrderByld(Long orderld) throws OrderException;

    public List<Order> usersOrderHistory(Long userld);

    public Order placedOrder(Long orderld) throws OrderException;

    public Order confirmedOrder(Long orderld)throws OrderException;

    public Order shippedOrder(Long orderld) throws OrderException;

    public Order deliveredOrder(Long orderld) throws OrderException;

    public Order cancledOrder(Long orderld) throws OrderException;
}
