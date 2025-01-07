package com.example.todolist.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     var authEncoded = request.getHeader("Authorization");

     byte[] decodedBytes = java.util.Base64.getDecoder().decode(authEncoded.substring("Basic".length()).trim());
        String decodedString = new String(decodedBytes);
        String[] decodedArray = decodedString.split(":");
        String username = decodedArray[0];
        String password = decodedArray[1];


    }
}
