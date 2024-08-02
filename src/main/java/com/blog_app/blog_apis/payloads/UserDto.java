package com.blog_app.blog_apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

  private int id;

  @NotBlank(message = "please enter the name") // this is  validation annotation
  private String name;

  @Email(message = "email is invalid")
  private String email;

  @NotBlank(message = "please set one password")
  @Size(min = 3, max = 5, message = "password should be in between 3 to 5")
  private String password;

  @NotBlank(message = "please fill about section")
  private String about;

  private List<CommentDto> comments = new ArrayList<>();
}
