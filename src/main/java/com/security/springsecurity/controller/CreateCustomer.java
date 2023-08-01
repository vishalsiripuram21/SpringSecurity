package com.security.springsecurity.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.springsecurity.Entity.Customer;
import com.security.springsecurity.Repository.CustomerRepo;

@RestController
public class CreateCustomer {

  @Autowired
  private CustomerRepo customerRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/create")
  public ResponseEntity<String> createUser(@RequestBody Customer customer) throws Exception{
    Optional<Customer> existcustomer = customerRepo.findByEmail(customer.getEmail());
    if(existcustomer.isPresent()){
      return new ResponseEntity<>("User name already exists",HttpStatus.FOUND);
    }
    String hashPassword = passwordEncoder.encode(customer.getPwd());
    // System.out.println(customer.getPassword()+" "+hashPassword);
    customer.setPassword(hashPassword);
    Customer savedCustomer =  customerRepo.save(customer);
    return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);

  }

  @PostMapping("/vishal")
  public String vish(){
    return "vishal";
  }
  
}
