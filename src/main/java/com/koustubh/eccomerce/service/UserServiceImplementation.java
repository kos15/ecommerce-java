package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.configs.JwtProvider;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("No user found with ID - " + userId);
    }

    @Override
    public User findUserProfileByJWT(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user != null){
            return user;
        }
        throw new UserException("No user found ..!");
    }
}
