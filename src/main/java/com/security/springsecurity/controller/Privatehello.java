package com.security.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.springsecurity.Entity.Customer;
import com.security.springsecurity.Repository.CustomerRepo;

@RestController
public class Privatehello {
  
  @Autowired
  private CustomerRepo repo;

  @GetMapping("/phello")
  public ResponseEntity<Customer> hello(){
    Customer cust = repo.findByEmail("rak").orElseThrow();
    return ResponseEntity.ok().body(cust);
  }
}
