package com.koustubh.eccomerce.request;

import com.koustubh.eccomerce.model.Size;

import java.util.HashSet;
import java.util.Set;

public class CreateProductRequest {

    private String title;
    private String description;
    private String brand;
    private int price;
    private int quantity;
    private int discountedPrice;
    private int discountPresent;
    private String color;
    private Set<Size> size = new HashSet<>();
    private String imageURL;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;


    public CreateProductRequest() {
    }

    public CreateProductRequest(String title, String description, String brand, int price, int quantity, int discountedPrice, int discountPrice, String color, Set<Size> size, String imageURL, String topLevelCategory, String secondLevelCategory, String thirdLevelCategory) {
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
        this.discountPresent = discountPrice;
        this.color = color;
        this.size = size;
        this.imageURL = imageURL;
        this.topLevelCategory = topLevelCategory;
        this.secondLevelCategory = secondLevelCategory;
        this.thirdLevelCategory = thirdLevelCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getDiscountPresent() {
        return discountPresent;
    }

    public void setDiscountPresent(int discountPresent) {
        this.discountPresent = discountPresent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<Size> getSize() {
        return size;
    }

    public void setSize(Set<Size> size) {
        this.size = size;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTopLevelCategory() {
        return topLevelCategory;
    }

    public void setTopLevelCategory(String topLevelCategory) {
        this.topLevelCategory = topLevelCategory;
    }

    public String getSecondLevelCategory() {
        return secondLevelCategory;
    }

    public void setSecondLevelCategory(String secondLevelCategory) {
        this.secondLevelCategory = secondLevelCategory;
    }

    public String getThirdLevelCategory() {
        return thirdLevelCategory;
    }

    public void setThirdLevelCategory(String thirdLevelCategory) {
        this.thirdLevelCategory = thirdLevelCategory;
    }
}
