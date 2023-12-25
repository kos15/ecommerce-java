package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.model.Product;
import com.koustubh.eccomerce.model.Rating;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.repository.RatingRepository;
import com.koustubh.eccomerce.request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService{

    @Autowired
    private ProductService productService;
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Rating rating = new Rating();
        rating.setRating(req.getRating());
        rating.setProduct(product);
        rating.setUser(user);
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductRating(productId);
    }
}
