package com.blog_app.blog_apis.controllers;

import com.blog_app.blog_apis.payloads.ApiResponse;
import com.blog_app.blog_apis.payloads.PostDto;
import com.blog_app.blog_apis.services.PostService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  @Autowired
  PostService postService;

  //post
  @PostMapping("/user/{userId}/category/{categoryId}/create_post")
  public ResponseEntity<PostDto> createPostHandler(
    @RequestBody PostDto postDto,
    @PathVariable("userId") int userId,
    @PathVariable("categoryId") int categoryID
  ) {
    PostDto createdPost = postService.createPost(postDto, userId, categoryID);
    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
  }

  //get all post
  @GetMapping("/")
  public ResponseEntity<List<PostDto>> getAllPostHandler(
    @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
    @RequestParam(value = "pageSize",defaultValue = "3",required = false) Integer pageSize
  ) {
    List<PostDto> posts = postService.getAllPost(pageNumber, pageSize);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  //get all post
  @GetMapping("/{postId}")
  public ResponseEntity<PostDto> getPostByPostIdHandler(
    @PathVariable Integer postId
  ) {
    PostDto post = postService.getPostById(postId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  // get post by user
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<PostDto>> getPostByUserHandler(
    @PathVariable Integer userId
  ) {
    List<PostDto> postsbyUser = postService.getPostbyUser(userId);
    return new ResponseEntity<>(postsbyUser, HttpStatus.OK);
  }

  //  get post by category
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<PostDto>> getPostByCategoryHandler(
    @PathVariable("categoryId") int categoryID
  ) {
    List<PostDto> postsbyCategory = postService.getPostbyCategory(categoryID);
    return new ResponseEntity<>(postsbyCategory, HttpStatus.OK);
  }

  // delete post
  @DeleteMapping("/{postId}")
  public ResponseEntity<ApiResponse> deletePostHandler(
    @PathVariable Integer postId
  ) {
    postService.deletePost(postId);
    return new ResponseEntity<>(
      new ApiResponse("post deleted", true),
      HttpStatus.OK
    );
  }

  //update post
  @PutMapping("/{postId}")
  public ResponseEntity<PostDto> updatePosthandler(
    @PathVariable Integer postId,
    @RequestBody PostDto postDto
  ) {
    PostDto post = postService.updatePost(postDto, postId);
    return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
  }
  // end of class
}
