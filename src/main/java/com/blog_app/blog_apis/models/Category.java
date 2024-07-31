package com.blog_app.blog_apis.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int categoryId;

  @Column(name = "category_title")
  private String categoryTitle;

  @Column(name = "category_description")
  private String categoryDescription;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  List<Post> posts = new ArrayList<>();
}
