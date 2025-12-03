package com.poly.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

public class Filter2 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Filter2: In ra thuộc tính
        System.out.println("Filter2 đọc: " + request.getAttribute("hello"));

        chain.doFilter(request, response);
    }
    // ... init và destroy
}