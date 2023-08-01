package com.security.springsecurity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPwd(){
    return this.password;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  private String email;
  private String password;
  private String role;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(this.getRole()));
    return null;
  }
  @Override
  public String getPassword() {

    return this.password;
  }
  @Override
  public String getUsername() {
 
    return this.email;
  }
  @Override
  public boolean isAccountNonExpired() {
    
    return true;
  }
  @Override
  public boolean isAccountNonLocked() {
    
    return true;
  }
  @Override
  public boolean isCredentialsNonExpired() {
   
    return true;
  }
  @Override
  public boolean isEnabled() {
   
    return true;
  }
  
}
