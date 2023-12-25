package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.model.Rating;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);
}
