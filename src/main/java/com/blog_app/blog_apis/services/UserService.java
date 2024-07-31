package com.blog_app.blog_apis.services;

import java.util.List;

import com.blog_app.blog_apis.payloads.UserDto;


public interface UserService {
  UserDto createUser(UserDto user);

  UserDto updateUser(UserDto user, Integer usderId);

  UserDto getUserById(Integer UserId);

  List<UserDto> getAllUser();

  void deleteUser(Integer userId);
}
