package com.security.springsecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.springsecurity.Entity.Roles;
import java.util.List;
import java.util.Optional;


public interface RoleRepo extends JpaRepository<Roles,Integer>{
  
  Optional<Roles> findByRole(String role); 

}
