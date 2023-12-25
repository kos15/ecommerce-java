package com.koustubh.eccomerce.controller;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.exception.UserException;
import com.koustubh.eccomerce.model.Rating;
import com.koustubh.eccomerce.model.Review;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.request.ReviewRequest;
import com.koustubh.eccomerce.service.ReviewService;
import com.koustubh.eccomerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJWT(jwt);
        Review review = reviewService.createReview(request, user);
        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable("productId") Long productId, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        User user = userService.findUserProfileByJWT(jwt);
        List<Review> reviews = reviewService.getAllReview(productId);

        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }
}
