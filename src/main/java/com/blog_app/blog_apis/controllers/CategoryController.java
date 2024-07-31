package com.blog_app.blog_apis.controllers;

import com.blog_app.blog_apis.payloads.ApiResponse;
import com.blog_app.blog_apis.payloads.CategoryDto;
import com.blog_app.blog_apis.services.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  //post category
  @PostMapping("/post_category")
  public ResponseEntity<CategoryDto> createCategoryHandler(
    @Valid @RequestBody CategoryDto categoryDto
  ) {
    CategoryDto createdCategory = categoryService.createCategory(categoryDto);
    return new ResponseEntity<>(createdCategory, HttpStatus.ACCEPTED);
  }

  //update category
  @PutMapping("/update_category/{id}")
  public ResponseEntity<CategoryDto> updateCategoryHandler(
    @Valid @RequestBody CategoryDto categoryDto,
    @PathVariable("id") int categoryId
  ) {
    CategoryDto updatedCategory = categoryService.updateCategory(
      categoryDto,
      categoryId
    );
    return new ResponseEntity<>(updatedCategory, HttpStatus.ACCEPTED);
  }

  //delete category
  @DeleteMapping("/delete_category/{id}")
  public ResponseEntity<ApiResponse> deleteCategoryHandler(
    @PathVariable("id") int categoryId
  ) {
    categoryService.deleteCategory(categoryId);

    return new ResponseEntity<>(
      new ApiResponse("Category deleted successfully ", true),
      HttpStatus.OK
    );
  }

  //get category
  @GetMapping("/get_category")
  public ResponseEntity<List<CategoryDto>> getCategoryHandler() {
    List<CategoryDto> categoryList = categoryService.getAllCategories();
    return new ResponseEntity<>(categoryList, HttpStatus.OK);
  }

  //get by id category
  @GetMapping("/get_category/{id}")
  public ResponseEntity<CategoryDto> getCategoryByIdHandler(
    @PathVariable("id") int categoryId
  ) {
    CategoryDto category = categoryService.getCategoryById(categoryId);
    return new ResponseEntity<>(category, HttpStatus.OK);
  }
}
