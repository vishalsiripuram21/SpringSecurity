package com.security.springsecurity.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
      http.
      cors(cors -> cors.configurationSource(new CorsConfigurationSource() {

          @Override
          public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
              CorsConfiguration corsObj = new CorsConfiguration();
              corsObj.setAllowCredentials(true);
              corsObj.setAllowedHeaders(Arrays.asList("*"));
              corsObj.setAllowedMethods(Arrays.asList("*"));
              corsObj.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
              corsObj.setMaxAge(3600L);
              return corsObj;
          }

      }));

      http.csrf(csrf -> csrf.ignoringRequestMatchers("*"))
      .authorizeHttpRequests((requests) ->
                      requests.requestMatchers("/phello", "/name").authenticated()
                              .requestMatchers("/welcome", "/hello").permitAll()
                              .requestMatchers(HttpMethod.POST, "/create").permitAll()
      )
              .formLogin(Customizer.withDefaults())
              .httpBasic(Customizer.withDefaults());
    return http.build();

  }


    // @Bean
    // public InMemoryUserDetailsManager userDetailService(){

    //   UserDetails admin = User.withDefaultPasswordEncoder()
    //                       .username("admin")
    //                       .password("12345")
    //                       .authorities("admin")
    //                       .build();
    //   UserDetails user = User.withDefaultPasswordEncoder()
    //                       .username("user")
    //                       .password("12345")
    //                       .authorities("read")
    //                       .build();
    //   return new InMemoryUserDetailsManager(admin,user);

    // }
  

    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
