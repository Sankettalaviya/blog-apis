package com.blog_app.blog_apis.payloads;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

  int postId;

  String title;

  String content;

  private String imageName;

  private Date addedDate;

  private CategoryDto category;

  private UserDto user;

  private List<CommentDto> comments = new ArrayList<>();

}
