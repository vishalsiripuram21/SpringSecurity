package com.security.springsecurity.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.springsecurity.Entity.Customer;
import com.security.springsecurity.Repository.CustomerRepo;

@RestController
public class Hello {

  @Autowired
  private CustomerRepo repo;

  @GetMapping("/hello")
  public ResponseEntity<Customer> hello(){
    Customer cust = repo.findByEmail("vishalsiripuram@gmail.com").orElseThrow();
    return ResponseEntity.ok().cacheControl(CacheControl.maxAge(5,TimeUnit.SECONDS)).body(cust);
  }
  
}
