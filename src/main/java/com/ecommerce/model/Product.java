package com.ecommerce.model;

import java.sql.Timestamp;

public class Product {

    private long productId;
    private long sellerId;
    private String productName;
    private String description;
    private String category;
    private double price;
    private int stockQuantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Product() {

    }

    public Product(long productId, long sellerId, String productName,
            String description, String category, double price,
            int stockQuantity, Timestamp createdAt,
            Timestamp updatedAt) {

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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
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

        return "Product ID : " + productId +
                "\nName : " + productName +
                "\nCategory : " + category +
                "\nPrice : ₹" + price +
                "\nStock : " + stockQuantity +
                "\n";
    }

}