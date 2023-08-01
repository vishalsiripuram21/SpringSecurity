package com.security.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.springsecurity.Entity.Customer;
import com.security.springsecurity.Repository.CustomerRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  CustomerRepo customerRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Customer user = customerRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    return user;
  }


  
}
