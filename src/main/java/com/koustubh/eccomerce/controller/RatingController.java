package com.koustubh.eccomerce.controller;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.Rating;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.request.RatingRequest;
import com.koustubh.eccomerce.service.RatingService;
import com.koustubh.eccomerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJWT(jwt);
        Rating rating = ratingService.createRating(request, user);
        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable("productId") Long productId,@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        User user = userService.findUserProfileByJWT(jwt);
        List<Rating> rating = ratingService.getProductsRating(productId);

        return new ResponseEntity<List<Rating>>(rating, HttpStatus.OK);
    }
}
