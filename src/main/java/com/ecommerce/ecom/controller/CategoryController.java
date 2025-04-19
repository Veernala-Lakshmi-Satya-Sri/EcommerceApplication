package com.ecommerce.ecom.controller;


import com.ecommerce.ecom.Payload.CategoryDTO;
import com.ecommerce.ecom.Payload.CategoryResponse;
import com.ecommerce.ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(){

       CategoryResponse categories = categoryService.getAllCategories();
       return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategories(@Valid @RequestBody CategoryDTO categoryDTO) {

         CategoryDTO savedCategoryDTO =categoryService.createCategory(categoryDTO);
         return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategories(@PathVariable Long categoryId) {
        CategoryDTO deletedCategory= categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);


    }


    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategories(@Valid @RequestBody CategoryDTO categoryDTO,
                                                   @PathVariable Long categoryId) {

            CategoryDTO updatedCategoryDto= categoryService.updateCategory(categoryDTO, categoryId);
            return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);


    }
}

/// trail
