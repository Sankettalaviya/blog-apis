package com.blog_app.blog_apis.services;

import com.blog_app.blog_apis.payloads.CommentDto;

public interface CommentService {
  CommentDto postComment(CommentDto commentDto, Integer postId, Integer userId);
  CommentDto updateComment(CommentDto commentDto, Integer commentId);

  void deleteComment(Integer commentId);
}
