package com.ecommerce.ecom.controller;


import com.ecommerce.ecom.model.Category;
import com.ecommerce.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){

       List<Category> categories = categoryService.getAllCategories();
       return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategories(@RequestBody Category category) {

         String status =categoryService.createCategory(category);
         return new ResponseEntity<String>(status,HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategories(@PathVariable Long categoryId) {

        try {
            String status= categoryService.deleteCategory(categoryId);
            return new ResponseEntity<String>(status, HttpStatus.OK);

        }
        catch (ResponseStatusException e) {
            return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
        }
    }


    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategories(@RequestBody Category category,
                                                   @PathVariable Long categoryId) {

        try {
            Category updatedCategory= categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<String>("Category with ID : "+categoryId+ " updated", HttpStatus.OK);

        }
        catch (ResponseStatusException e) {
            return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
        }
    }
}
