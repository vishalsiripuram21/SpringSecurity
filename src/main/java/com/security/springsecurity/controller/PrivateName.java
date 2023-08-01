package com.security.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateName {
  
  @GetMapping("/name")
  public String name(){
    return "my name is vishal";
  }
}
