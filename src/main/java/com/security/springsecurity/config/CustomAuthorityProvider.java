package com.security.springsecurity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.security.springsecurity.Entity.Customer;
import com.security.springsecurity.Repository.CustomerRepo;

@Component
public class CustomAuthorityProvider implements AuthenticationProvider{

  @Autowired
  private CustomerRepo customerRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    
    String username = authentication.getName();
    Customer customer = customerRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    if(passwordEncoder.matches( authentication.getCredentials().toString(), customer.getPwd())){
      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(customer.getRole()));
      return new UsernamePasswordAuthenticationToken(customer, authentication.getCredentials().toString(),authorities);
    }
    else{
      throw new BadCredentialsException("Incorrect password");
    }

  }

  @Override
  public boolean supports(Class<?> authentication) {
    // TODO Auto-generated method stub
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
  
}
