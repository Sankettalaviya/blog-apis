package com.blog_app.blog_apis.controllers;

import com.blog_app.blog_apis.payloads.ApiResponse;
import com.blog_app.blog_apis.payloads.PostDto;
import com.blog_app.blog_apis.payloads.PostResponse;
import com.blog_app.blog_apis.services.FileService;
import com.blog_app.blog_apis.services.PostService;
import com.blog_app.blog_apis.services.impl.FileServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  @Autowired
  PostService postService;

  @Autowired
  FileServiceImpl fileServiceImpl;

  @Value("${project.image}")
  String path;

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
  public ResponseEntity<PostResponse> getAllPostHandler(
    @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
    @RequestParam(defaultValue = "3", required = false) Integer pageSize,
    @RequestParam(defaultValue = "postId", required = false) String sortBy,
    @RequestParam(defaultValue = "asc", required = false) String scrollDir
  ) {
    PostResponse postResponse = postService.getAllPost(
      pageNumber,
      pageSize,
      sortBy,
      scrollDir
    );
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
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

  @GetMapping("/search")
  public ResponseEntity<List<PostDto>> searchHandler(
    @RequestParam("keyword") String keyword
  ) {
    List<PostDto> postDtos = postService.searchPost(keyword);
    return new ResponseEntity<>(postDtos, HttpStatus.ACCEPTED);
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

  // post image
  @PostMapping("/upload_image/{postId}")
  public ResponseEntity<PostDto> postImage(
    @RequestBody MultipartFile image,
    @PathVariable Integer postId
  ) throws IOException {
    PostDto postDto = postService.getPostById(postId);

    String uploadImage = fileServiceImpl.uploadImage(path, image);
    System.out.println(uploadImage);
    postDto.setImageName(uploadImage);
    PostDto updatedPost = postService.updatePost(postDto, postId);
    return new ResponseEntity<>(updatedPost, HttpStatus.ACCEPTED);
  }

  // get image
  @GetMapping(
    value = "/image/{imageName}",
    produces = MediaType.IMAGE_JPEG_VALUE
  )
  public void getImage(
    @PathVariable String imageName,
    HttpServletResponse response
  ) throws IOException {
    InputStream resource = fileServiceImpl.getResources(path, imageName);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource, response.getOutputStream());
  }
  // end of class
}
