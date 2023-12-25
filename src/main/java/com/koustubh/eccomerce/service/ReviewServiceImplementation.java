package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.model.Product;
import com.koustubh.eccomerce.model.Review;
import com.koustubh.eccomerce.model.User;
import com.koustubh.eccomerce.repository.ReviewRepository;
import com.koustubh.eccomerce.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;
    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();
        review.setReview(req.getReview());
        review.setProduct(product);
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductReview(productId);
    }
}
