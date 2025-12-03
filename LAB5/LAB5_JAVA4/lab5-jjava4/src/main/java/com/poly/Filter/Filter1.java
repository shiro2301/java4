package com.poly.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

public class Filter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Filter1: Đặt thuộc tính
        request.setAttribute("hello", "Tôi là filter 1");
        
        chain.doFilter(request, response);
    }
    // ... init và destroy
}
