package com.example.baicuangu.model;

import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private Double price;
    private LocalDateTime outOfDate;

    private Category category;

    public Product() {
    }

    public Product(int id, String name, Double price, LocalDateTime outOfDate, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.outOfDate = outOfDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(LocalDateTime outOfDate) {
        this.outOfDate = outOfDate;
    }

}
