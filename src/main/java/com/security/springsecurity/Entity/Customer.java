package com.security.springsecurity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Customer{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  public ArrayList<String> getRole() {
    return role;
  }
  public void setRole(ArrayList<String> role) {
    this.role = role;
  }
  private String email;


  private String password;
  private ArrayList<String> role;

  @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
  @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  @JsonIgnore
  private Set<Roles> customer;
  public Set<Roles> getCustomer() {
    return customer;
  }
  public void setCustomer(Set<Roles> customer) {
    this.customer = customer;
  }

 
 
 
 
  
}