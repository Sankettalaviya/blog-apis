package com.blog_app.blog_apis.repositories;

import com.blog_app.blog_apis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {}
