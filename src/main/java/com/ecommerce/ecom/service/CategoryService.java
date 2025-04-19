package com.ecommerce.ecom.service;

import com.ecommerce.ecom.Payload.CategoryDTO;
import com.ecommerce.ecom.Payload.CategoryResponse;
import com.ecommerce.ecom.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
