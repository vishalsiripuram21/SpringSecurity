package com.security.springsecurity.filters;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CsrfCookieFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    if (null != csrfToken.getHeaderName()) {
      // csrfToken.getHeaderName()
      response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
      response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
    }
    filterChain.doFilter(request, response);
  }

}
