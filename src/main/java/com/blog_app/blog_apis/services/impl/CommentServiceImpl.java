package com.blog_app.blog_apis.services.impl;

import com.blog_app.blog_apis.exceptions.ResourceNotFoundException;
import com.blog_app.blog_apis.models.Comment;
import com.blog_app.blog_apis.models.Post;
import com.blog_app.blog_apis.models.User;
import com.blog_app.blog_apis.payloads.CommentDto;
import com.blog_app.blog_apis.repositories.CommentRepo;
import com.blog_app.blog_apis.repositories.PostRepo;
import com.blog_app.blog_apis.repositories.UserRepo;
import com.blog_app.blog_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentRepo commentRepo;

  @Autowired
  PostRepo postRepo;

  @Autowired
  UserRepo userRepo;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public CommentDto postComment(
    CommentDto commentDto,
    Integer postId,
    Integer userId
  ) {
    Post post = postRepo
      .findById(postId)
      .orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
    User user = userRepo
      .findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
    Comment comment = modelMapper.map(commentDto, Comment.class);
    comment.setPost(post);
    comment.setUser(user);
    Comment savedComment = commentRepo.save(comment);
    return modelMapper.map(savedComment, CommentDto.class);
  }

  @Override
  public void deleteComment(Integer commentId) {
    commentRepo.deleteById(commentId);
  }

  @Override
  public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
    Comment comment = commentRepo
      .findById(commentId)
      .orElseThrow(() ->
        new ResourceNotFoundException("comment", "id", commentId)
      );
    comment.setComment(commentDto.getComment());
    Comment updatedComment = commentRepo.save(comment);
    return modelMapper.map(updatedComment, CommentDto.class);
  }
}
