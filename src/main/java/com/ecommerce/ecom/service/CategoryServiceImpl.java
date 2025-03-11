package com.ecommerce.ecom.service;

import com.ecommerce.ecom.model.Category;
import com.ecommerce.ecom.repositaries.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl  implements CategoryService{

  //  private final List<Category> categories= new ArrayList<>();



    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategories() {

        return categoryRepo.findAll();
    }

    @Override
    public String createCategory(Category category) {
        categoryRepo.save(category);
        return "category with ID : " +category.getCategoryId()+ " created";

    }

    @Override
    public String deleteCategory(Long categoryId) {
//        Category category= categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst().orElse(null);
//        if(category== null) {
//            return "Category not found";
//        }

//        Category category= categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
//
//        categories.remove(category);


//        List<Category> categories= new ArrayList<>();
//        Category category= categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
//
//       categoryRepo.delete(category);


     Category existingCategory=categoryRepo.findById(categoryId)
             .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
     categoryRepo.delete(existingCategory);
        return "Category with ID : "+categoryId+" deleted";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
//        List<Category> categories= new ArrayList<>();
//        Optional<Category>  optionalCategory=categories.stream()
//                .filter(c-> c.getCategoryId().equals(categoryId))
//                .findFirst();
//
//        if(optionalCategory.isPresent()) {
//            Category existingCategory= optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category savedCategory= categoryRepo.save(existingCategory);
//            return savedCategory;
//        }
//        else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found");
//        }


        Category existingCategory= categoryRepo.findById(categoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with ID : "+ categoryId+" not found"));
        category.setCategoryId(categoryId);
        return categoryRepo.save(category);
    }
}
