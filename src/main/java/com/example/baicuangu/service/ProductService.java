package com.example.baicuangu.service;

import com.example.baicuangu.model.Category;
import com.example.baicuangu.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final List<Product> products;
    private static ProductService productService;

    public ProductService() {
        products = new ArrayList<>();
    }
    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        Product product = getById(id);
        if (product != null) {
            products.remove(product);
        }
    }

    public void deleteByCategory(Category category) {
        List<Product> productDelete = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                productDelete.add(product);
            }
        }
        products.removeAll(productDelete);
    }

    public List<Product> searchByName(String name) {
        List<Product> productSearch = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                productSearch.add(product);
            }
        }
        return productSearch;
    }
}
