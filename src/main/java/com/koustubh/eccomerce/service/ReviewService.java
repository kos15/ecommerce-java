package com.koustubh.eccomerce.service;


import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.model.Review;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);
}
