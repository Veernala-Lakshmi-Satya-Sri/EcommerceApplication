package com.ecommerce.ecom.service;

import com.ecommerce.ecom.Exceptions.APIException;
import com.ecommerce.ecom.Exceptions.ResourceNotFoundException;
import com.ecommerce.ecom.Payload.CategoryDTO;
import com.ecommerce.ecom.Payload.CategoryResponse;
import com.ecommerce.ecom.model.Category;
import com.ecommerce.ecom.repositaries.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService{

  //  private final List<Category> categories= new ArrayList<>();



    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder= sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageDetails= PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Category> categoryPage=categoryRepo.findAll(pageDetails);
        List<Category> categories= categoryPage.getContent();

        // List<Category> categories= categoryRepo.findAll();
           if (categories.isEmpty()) {
            throw new APIException("No Categories present");
        }

        List<CategoryDTO> categoryDTOS= categories.stream().map(
                category -> modelMapper.map(category, CategoryDTO.class)
        ).toList();
      CategoryResponse categoryResponse= new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());



        return categoryResponse;
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=  modelMapper.map(categoryDTO, Category.class);
        Category foundCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if (foundCategory != null)
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists !!!");
        //category.setCategoryId(nextId++);
        categoryRepo.save(category);

    Category savedCategory = categoryRepo.save(category);
    CategoryDTO savedCategoryDto =modelMapper.map(savedCategory,CategoryDTO.class);
        return savedCategoryDto;

    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
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
             .orElseThrow(()-> new ResourceNotFoundException("Catogory", "catogoryId",categoryId));
      categoryRepo.delete(existingCategory);
        return modelMapper.map(existingCategory, CategoryDTO.class);

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
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

        Category category = modelMapper.map(categoryDTO, Category.class);
        Category existingCategory= categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Catogory", "catogoryId",categoryId));
        category.setCategoryId(categoryId);
       Category savedCategory=  categoryRepo.save(category);
         return modelMapper.map(savedCategory,CategoryDTO.class);
    }
}
