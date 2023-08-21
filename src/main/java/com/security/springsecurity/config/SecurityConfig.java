package com.security.springsecurity.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.security.springsecurity.filters.CsrfCookieFilter;
import com.security.springsecurity.filters.JwtTokenGeneratorFilter;
import com.security.springsecurity.filters.JwtTokenValidatorFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
    requestHandler.setCsrfRequestAttributeName(null);

    http.
        sessionManagement(sessionmanage -> {
          sessionmanage.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        })
        .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {

          @Override
          public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration corsObj = new CorsConfiguration();
            corsObj.setAllowCredentials(true);
            corsObj.setAllowedHeaders(Arrays.asList("*"));
            corsObj.setAllowedMethods(Arrays.asList("*"));
            corsObj.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
            corsObj.setExposedHeaders(Arrays.asList("Authorization"));
            corsObj.setMaxAge(3600L);
            return corsObj;
          }

        }));
    http.csrf((csrf) -> {
      csrf.csrfTokenRequestHandler(requestHandler)
          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    })
        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
        .authorizeHttpRequests(
            (requests) -> requests.requestMatchers("/phello").hasAuthority("user")
            .requestMatchers( "/vishal").hasAuthority("admin")
            .requestMatchers( "/name").hasAnyAuthority("admin","user")
            .requestMatchers("/welcome","/create","/hello").permitAll()

        // .requestMatchers(HttpMethod.POST, "/create").permitAll()
        )
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());
        // .headers(headers -> headers.httpStrictTransportSecurity().includeSubDomains(false));
    return http.build();

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
