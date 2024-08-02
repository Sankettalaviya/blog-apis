package com.blog_app.blog_apis.controllers;

import com.blog_app.blog_apis.payloads.ApiResponse;
import com.blog_app.blog_apis.payloads.CommentDto;
import com.blog_app.blog_apis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

  @Autowired
  CommentService commentService;

  @PostMapping("/user/{userId}/post/{postId}")
  public ResponseEntity<CommentDto> postCommentHandler(
    @RequestBody CommentDto commentDto,
    @PathVariable Integer userId,
    @PathVariable Integer postId
  ) {
    CommentDto postComment = commentService.postComment(
      commentDto,
      postId,
      userId
    );
    return new ResponseEntity<>(postComment, HttpStatus.CREATED);
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<CommentDto> updateCommentHandler(
    @RequestBody CommentDto commentDto,
    @PathVariable Integer commentId
  ) {
      CommentDto updatedComment = commentService.updateComment(commentDto, commentId);
      return new ResponseEntity<>(updatedComment,HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<ApiResponse> deleteCommentHandler(
    @PathVariable Integer commentId
  ) {
    commentService.deleteComment(commentId);

    return new ResponseEntity<>(
      new ApiResponse("comment deleted", true),
      HttpStatus.CREATED
    );
  }
}
