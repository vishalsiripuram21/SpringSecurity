package com.security.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Privatehello {
  
  @GetMapping("/phello")
  public String hello(){
    return "hey private hello";
  }
}
