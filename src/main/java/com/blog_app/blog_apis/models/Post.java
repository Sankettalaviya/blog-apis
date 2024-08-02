package com.blog_app.blog_apis.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.*;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int postId;

  @Column(name = "post_title")
  private String title;

  @Column(name = "psot_content")
  private String content;

  private String imageName;

  private Date addedDate;

  @ManyToOne
  private Category category;

  @ManyToOne
  private User user;

  @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
  List<Comment> comments;
}
