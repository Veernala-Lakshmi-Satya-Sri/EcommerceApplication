package com.ecommerce.ecom.service;

import com.ecommerce.ecom.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    String createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
