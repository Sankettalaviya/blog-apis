package com.blog_app.blog_apis.services;

import com.blog_app.blog_apis.payloads.PostDto;
import com.blog_app.blog_apis.payloads.PostResponse;
import java.util.List;

public interface PostService {
  //create
  PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

  //update
  PostDto updatePost(PostDto postDto, Integer postId);

  //delete
  void deletePost(Integer postId);

  //get all post
  PostResponse getAllPost(
    Integer pageNumber,
    Integer pageSize,
    String sortBy,
    String scrollDir
  );

  // get post by post id
  PostDto getPostById(Integer id);

  // get by  category id
  List<PostDto> getPostbyCategory(Integer categoryId);

  // get by  user id
  List<PostDto> getPostbyUser(Integer userId);

  //search posts
  List<PostDto> searchPost(String keyword);
}
