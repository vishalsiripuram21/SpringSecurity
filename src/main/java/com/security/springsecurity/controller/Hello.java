package com.security.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {


  @GetMapping("/hello")
  public String hello(){
    return "public hello controller";
  }
  
}
