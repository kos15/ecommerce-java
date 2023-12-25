package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.model.Product;
import com.koustubh.eccomerce.request.CreateProductRequest;
import com.koustubh.eccomerce.exception.ProductException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public Product createProduct(CreateProductRequest req) throws ProductException;

    public String deleteProduct(Long id) throws ProductException;

    public Product updateProduct(Long id, Product req) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductByCategory(String category) throws ProductException;

    public Page<Product> getAllProduct(String category, List<String> color, List<String> size, Integer minPrice, Integer maxPrice,Integer minDiscount, String sort, String stock,Integer pageNumber, Integer pageSize) throws ProductException;
}
