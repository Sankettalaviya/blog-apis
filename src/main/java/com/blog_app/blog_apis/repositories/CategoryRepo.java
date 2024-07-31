package com.blog_app.blog_apis.repositories;

import com.blog_app.blog_apis.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {}
