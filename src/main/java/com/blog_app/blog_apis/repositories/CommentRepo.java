package com.blog_app.blog_apis.repositories;

import com.blog_app.blog_apis.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {}
