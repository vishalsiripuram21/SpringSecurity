package com.security.springsecurity.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
import com.security.springsecurity.Entity.Roles;
import com.security.springsecurity.Repository.CustomerRepo;
import com.security.springsecurity.Repository.RoleRepo;

@RestController
public class CreateCustomer {

  @Autowired
  private CustomerRepo customerRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleRepo roleRepo;

  @PostMapping("/create")
  public ResponseEntity<Customer> createUser(@RequestBody Customer customer) throws Exception{
    Optional<Customer> existcustomer = customerRepo.findByEmail(customer.getEmail());
    if(existcustomer.isPresent()){
      return new ResponseEntity<Customer>(existcustomer.get(),HttpStatus.ALREADY_REPORTED);
    }
    String hashPassword = passwordEncoder.encode(customer.getPwd());
    // System.out.println(customer.getPassword()+" "+hashPassword);
    Set<Roles> rolesSet = new HashSet<>();
    for (String role : customer.getRole()) {
     Roles custRole = roleRepo.findByRole(role).orElseThrow();
     rolesSet.add(custRole);
    }
    customer.setCustomer(rolesSet);
    customer.setPassword(hashPassword);
    Customer savedCustomer =  customerRepo.save(customer);
    return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);

  }

  @GetMapping("/vishal")
  public String vish(){
   Customer cus = customerRepo.findByEmail("rak").orElseThrow();
   Set<Roles> roles = cus.getCustomer();
   for (Roles roles2 : roles) {
    System.err.println(roles2.getRole());
   }
   return "vishal";
  }


  
}
