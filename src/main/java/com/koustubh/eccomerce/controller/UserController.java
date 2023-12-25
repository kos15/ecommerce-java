package com.koustubh.eccomerce.controller;

import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userService.findUserProfileByJWT(jwt);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
