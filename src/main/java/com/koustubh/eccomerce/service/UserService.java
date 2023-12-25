package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJWT(String jwt) throws UserException;
}
