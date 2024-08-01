package com.blog_app.blog_apis.repositories;

import com.blog_app.blog_apis.models.Category;
import com.blog_app.blog_apis.models.Post;
import com.blog_app.blog_apis.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
      List<Post> findByCategory(Category category);

      List<Post> findByUser(User user);

      List<Post> findByTitleContaining(String title);



}
