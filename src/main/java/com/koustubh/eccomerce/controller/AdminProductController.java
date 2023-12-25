package com.koustubh.eccomerce.controller;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.model.Product;
import com.koustubh.eccomerce.request.CreateProductRequest;
import com.koustubh.eccomerce.response.ApiResponse;
import com.koustubh.eccomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) throws ProductException {
        Product product = productService.createProduct(request);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productid) throws ProductException {
        productService.deleteProduct(productid);
        ApiResponse res = new ApiResponse();
        res.setMessage("product deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() throws ProductException {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req, @PathVariable Long productId) throws ProductException{
        Product product = productService.updateProduct(productId, req);
        return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProducts(@RequestBody CreateProductRequest[] requests) throws ProductException {
        for (CreateProductRequest product: requests){
            productService.createProduct(product);
        }
        ApiResponse response = new ApiResponse();
        response.setStatus(true);
        response.setMessage("Products created successfully");
        return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
    }


}
