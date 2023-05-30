package com.example.baicuangu.service;

import com.example.baicuangu.model.Category;
import com.example.baicuangu.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private final List<Category> categoryList;
    private static CategoryService categoryService;

    public CategoryService() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Drinks"));
        categoryList.add(new Category(2, "Dry Food"));
    }

    public static CategoryService getInstance() {
        if (categoryService == null) {
            categoryService = new CategoryService();
        }
        return categoryService;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void addClasses(Category category) {
        categoryList.add(category);
    }

    public Category getById(int id) {
        for (Category category : categoryList) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        Category category = getById(id);
        if (category != null) {
            categoryList.remove(category);
        }
    }



}
