package com.blog_app.blog_apis.services.impl;

import com.blog_app.blog_apis.exceptions.ResourceNotFoundException;
import com.blog_app.blog_apis.models.Category;
import com.blog_app.blog_apis.payloads.CategoryDto;
import com.blog_app.blog_apis.repositories.CategoryRepo;
import com.blog_app.blog_apis.services.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImple implements CategoryService {

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  CategoryRepo categoryRepo;

  @Override
  public CategoryDto createCategory(CategoryDto categoryDto) {
    Category category = this.modelMapper.map(categoryDto, Category.class);
    Category savedCategory = categoryRepo.save(category);
    return this.modelMapper.map(savedCategory, CategoryDto.class);
  }

  @Override
  public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
    
    Category category = categoryRepo
      .findById(categoryId)
      .orElseThrow(() ->
        new ResourceNotFoundException("category", "id", categoryId)
      );
    category.setCategoryId(categoryId);
    category.setCategoryTitle(categoryDto.getCategoryTitle());
    category.setCategoryDescription(categoryDto.getCategoryDescription());
    Category updatedCategory = categoryRepo.save(category);
    return this.modelMapper.map(updatedCategory, CategoryDto.class);
  }

  @Override
  public void deleteCategory(int categoryId) {
    categoryRepo
      .findById(categoryId)
      .orElseThrow(() ->
        new ResourceNotFoundException("category", "id", categoryId)
      );
    categoryRepo.deleteById(categoryId);
  }

  @Override
  public List<CategoryDto> getAllCategories() {
    List<Category> categories = categoryRepo.findAll();
    List<CategoryDto> categoryDtos = categories
      .stream()
      .map(cat -> this.modelMapper.map(cat, CategoryDto.class))
      .collect(Collectors.toList());
    return categoryDtos;
  }

  @Override
  public CategoryDto getCategoryById(int categoryId) {
    Category category = categoryRepo
      .findById(categoryId)
      .orElseThrow(() ->
        new ResourceNotFoundException("category", "id", categoryId)
      );
    return this.modelMapper.map(category, CategoryDto.class);
  }
}
