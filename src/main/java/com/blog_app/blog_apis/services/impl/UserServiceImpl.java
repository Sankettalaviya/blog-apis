package com.blog_app.blog_apis.services.impl;

import com.blog_app.blog_apis.exceptions.ResourceNotFoundException;
import com.blog_app.blog_apis.models.User;
import com.blog_app.blog_apis.payloads.UserDto;
import com.blog_app.blog_apis.repositories.UserRepo;
import com.blog_app.blog_apis.services.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public UserDto createUser(UserDto userDto) {
    User user = dtoToUser(userDto);
    User savedUser = this.userRepo.save(user);
    return userToDto(savedUser);
  }

  @Override
  public List<UserDto> getAllUser() {
    List<User> users = userRepo.findAll();

    List<UserDto> userDtos = users
      .stream()
      .map(user -> this.userToDto(user))
      .collect(Collectors.toList());

    return userDtos;
  }

  @Override
  public UserDto getUserById(Integer userId) {
    User user2 = userRepo
      .findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    return userToDto(user2);
  }

  @Override
  public UserDto updateUser(UserDto userDto, Integer usderId) {
    userRepo
      .findById(usderId)
      .orElseThrow(() -> new ResourceNotFoundException("User", "id", usderId));
    User user1 = dtoToUser(userDto);
    user1.setId(usderId);
    user1.setName(userDto.getName());
    user1.setEmail(userDto.getEmail());
    user1.setPassword(userDto.getPassword());
    user1.setAbout(userDto.getAbout());
    User updatedUser = userRepo.save(user1);
    return userToDto(updatedUser);
  }

  @Override
  public void deleteUser(Integer userId) {
    User user = userRepo
      .findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
    userRepo.delete(user);
  }

  public UserDto userToDto(User user) {
    UserDto userDto = this.modelMapper.map(user, UserDto.class);
    // userDto.setId(user.getId());
    // userDto.setName(user.getName());
    // userDto.setEmail(user.getEmail());
    // userDto.setPassword(user.getPassword());
    // userDto.setAbout(user.getAbout());
    return userDto;
  }

  public User dtoToUser(UserDto userDto) {
    User user = modelMapper.map(userDto, User.class);

    return user;
  }
}
