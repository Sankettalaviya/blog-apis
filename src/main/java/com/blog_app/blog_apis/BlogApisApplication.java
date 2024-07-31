package com.blog_app.blog_apis;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApisApplication {

  public static void main(String[] args) {
    SpringApplication.run(BlogApisApplication.class, args);
  }

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
