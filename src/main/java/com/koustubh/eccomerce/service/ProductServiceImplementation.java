package com.koustubh.eccomerce.service;

import com.koustubh.eccomerce.exception.ProductException;
import com.koustubh.eccomerce.model.Category;
import com.koustubh.eccomerce.model.Product;
import com.koustubh.eccomerce.repository.CategoryRepository;
import com.koustubh.eccomerce.repository.ProductRepository;
import com.koustubh.eccomerce.request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;
    @Override
    public Product createProduct(CreateProductRequest req) throws ProductException {
        // Check for Category is already present inside database if not will add a new category
        Category topLevelCategory = categoryRepository.findByName(req.getTopLevelCategory());
        if(topLevelCategory == null){
            Category top = new Category();
            top.setLevel(1);
            top.setName(req.getTopLevelCategory());
            topLevelCategory = categoryRepository.save(top);
        }
        Category secondLevelCategory = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), topLevelCategory);
        if(secondLevelCategory == null){
            Category second = new Category();
            second.setLevel(2);
            second.setName(req.getSecondLevelCategory());
            second.setParentCategory(topLevelCategory);
            secondLevelCategory = categoryRepository.save(second);
        }
        Category thridLevelCategory = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), secondLevelCategory);
        if(thridLevelCategory == null){
            Category third = new Category();
            third.setLevel(3);
            third.setName(req.getThirdLevelCategory());
            third.setParentCategory(secondLevelCategory);
            thridLevelCategory = categoryRepository.save(third);
        }

        // Create a new Product and add all the details in product object
        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountPresent(req.getDiscountPresent());
        product.setImageURL(req.getImageURL());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thridLevelCategory);
        product.setCreatedAt(LocalDateTime.now());

        // Create a new product inside database
        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long id) throws ProductException {
        Product product = findProductById(id);
        product.getSizes().clear();
        productRepository.delete(product);

        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long id, Product req) throws ProductException {
        Product product = findProductById(id);
        if(product != null){
            return productRepository.save(req);
        }
        return null;
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return product.get();
        }
        throw new ProductException("No product found with id -" + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) throws ProductException {
        return null;
    }

    @Override
    public List<Product> findAllProducts() throws ProductException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new ProductException("No Products available..!");
        }
        return products;
    }


    @Override
    public Page<Product> getAllProduct(String category, List<String> color, List<String> size, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) throws ProductException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProduct(category,minPrice,maxPrice, minDiscount,sort);

        if(!color.isEmpty()){
            products.stream()
                    .filter(product -> color.stream()
                            .anyMatch(clr -> clr.equalsIgnoreCase(product.getColor()))
                    ).collect(Collectors.toList());
        }

        if(stock != null){
            if(stock.equalsIgnoreCase("in_stock")){
            products.stream().filter(product -> product.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equalsIgnoreCase("out_of_stock")) {
            products.stream().filter(product -> product.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        Page<Product> filteredProducts = new PageImpl<Product>(pageContent,pageable,products.size());
        return filteredProducts;
    }
}
