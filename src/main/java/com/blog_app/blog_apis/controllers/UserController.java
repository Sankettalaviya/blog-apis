package com.blog_app.blog_apis.controllers;

import com.blog_app.blog_apis.payloads.UserDto;
import com.blog_app.blog_apis.services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  // post api
  @PostMapping("/create_user")
  public ResponseEntity<UserDto> createUserHandler(
    @Valid @RequestBody UserDto userDto
  ) {
    UserDto createduser = userService.createUser(userDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(createduser);
  }

  // get api
  @GetMapping("/get_user")
  public ResponseEntity<List<UserDto>> getUsersHandler() {
    List<UserDto> users = userService.getAllUser();
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
  }

  //get by id api
  @GetMapping("/get_user/{id}")
  public ResponseEntity<UserDto> getUserByIdHandler(@PathVariable int id) {
    UserDto user = userService.getUserById(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
  }

  //update api
  @PutMapping("/update_user/{id}")
  public ResponseEntity<UserDto> updateUserByIdHandler(
    @PathVariable int id,
    @Valid @RequestBody UserDto userDto
  ) {
    UserDto user = userService.updateUser(userDto, id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
  }

  //delete api

  @DeleteMapping("/delete_user/{id}")
  public ResponseEntity<UserDto> updateUserByIdHandler(@PathVariable int id) {
    userService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
