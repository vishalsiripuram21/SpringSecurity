package com.security.springsecurity.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import com.security.springsecurity.Entity.Customer;


public interface CustomerRepo extends JpaRepository<Customer,Integer> {
 
  Optional<Customer> findByEmail(String email);
}
