package com.blog_app.blog_apis.services;

import com.blog_app.blog_apis.payloads.CategoryDto;
import java.util.List;

public interface CategoryService {
  CategoryDto createCategory(CategoryDto categoryDto);

  CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

  void deleteCategory(int categoryId);

  List<CategoryDto> getAllCategories();

  CategoryDto getCategoryById(int categoryId);
}
