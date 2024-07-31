package com.blog_app.blog_apis.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

  private int categoryId;

  @NotBlank(message = "please enter category title")
  @Size(min = 3, max = 10, message = "title size shuold be between 3 to 10")
  private String categoryTitle;

  @NotBlank(message = "please enter category description")
  @Size(min = 10, message = "description size shuold be minimum 10 ")
  private String categoryDescription;
}
