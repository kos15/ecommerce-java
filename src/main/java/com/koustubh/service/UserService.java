package com.koustubh.service;

import com.koustubh.exception.UserException;
import com.koustubh.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJWT(String jwt) throws UserException;
}
