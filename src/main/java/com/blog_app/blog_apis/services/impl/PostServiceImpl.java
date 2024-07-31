package com.blog_app.blog_apis.services.impl;

import com.blog_app.blog_apis.exceptions.ResourceNotFoundException;
import com.blog_app.blog_apis.models.Category;
import com.blog_app.blog_apis.models.Post;
import com.blog_app.blog_apis.models.User;
import com.blog_app.blog_apis.payloads.PostDto;
import com.blog_app.blog_apis.repositories.CategoryRepo;
import com.blog_app.blog_apis.repositories.PostRepo;
import com.blog_app.blog_apis.repositories.UserRepo;
import com.blog_app.blog_apis.services.PostService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  PostRepo postRepo;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  UserRepo userRepo;

  @Autowired
  CategoryRepo categoryRepo;

  @Override
  public PostDto createPost(
    PostDto postDto,
    Integer userId,
    Integer categoryId
  ) {
    Post post = modelMapper.map(postDto, Post.class);
    User user = userRepo
      .findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
    Category category = categoryRepo
      .findById(categoryId)
      .orElseThrow(() ->
        new ResourceNotFoundException("category", "id", categoryId)
      );
    post.setImageName("default.png");
    post.setAddedDate(new Date());
    post.setUser(user);
    post.setCategory(category);
    Post createdPost = postRepo.save(post);
    return modelMapper.map(createdPost, PostDto.class);
  }

  @Override
  public PostDto updatePost(PostDto postDto, Integer postId) {
    Post post = postRepo
      .findById(postId)
      .orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
    post.setTitle(postDto.getTitle());
    post.setContent(postDto.getContent());
    Post updatedPost = postRepo.save(post);
    return modelMapper.map(updatedPost, PostDto.class);
  }

  @Override
  public PostDto getPostById(Integer postId) {
    Post post = postRepo
      .findById(postId)
      .orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

    return modelMapper.map(post, PostDto.class);
  }

  @Override
  public void deletePost(Integer postId) {
    postRepo
      .findById(postId)
      .orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
    postRepo.deleteById(postId);
  }

  @Override
  public List<PostDto> getAllPost() {
    List<Post> posts = postRepo.findAll();
    List<PostDto> postDtos = posts
      .stream()
      .map(post -> modelMapper.map(post, PostDto.class))
      .collect(Collectors.toList());
    return postDtos;
  }

  @Override
  public List<PostDto> getPostbyCategory(Integer categoryId) {
    Category category = categoryRepo
      .findById(categoryId)
      .orElseThrow(() ->
        new ResourceNotFoundException("category", "id", categoryId)
      );
    List<Post> posts = postRepo.findByCategory(category);

    List<PostDto> postDtos = posts
      .stream()
      .map(post -> modelMapper.map(post, PostDto.class))
      .collect(Collectors.toList());
    return postDtos;
  }

  @Override
  public List<PostDto> getPostbyUser(Integer userId) {
    User user = userRepo
      .findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

    List<Post> posts = postRepo.findByUser(user);

    List<PostDto> postDtos = posts
      .stream()
      .map(post -> modelMapper.map(post, PostDto.class))
      .collect(Collectors.toList());
    return postDtos;
  }

  @Override
  public List<PostDto> searchPost(String keyword) {
    return List.of();
  }
}
