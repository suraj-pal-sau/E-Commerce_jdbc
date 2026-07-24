package com.irfan.model;

import java.sql.Timestamp;

public class Product {

    private int productId;
    private Integer sellerId;
    private String productName;
    private String description;
    private String category;
    private double price;
    private int stockQuantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Default Constructor
    public Product() {
    }

    // Constructor without timestamps
    public Product(int productId, Integer sellerId, String productName,
                   String description, String category,
                   double price, int stockQuantity) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Constructor with all fields
    public Product(int productId, Integer sellerId, String productName,
                   String description, String category,
                   double price, int stockQuantity,
                   Timestamp createdAt, Timestamp updatedAt) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", sellerId=" + sellerId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
