package com.koustubh.service;

import com.koustubh.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;
}
