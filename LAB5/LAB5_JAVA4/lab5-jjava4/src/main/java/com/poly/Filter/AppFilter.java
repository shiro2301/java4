package com.poly.Filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import com.poly.daoImpl.LogDAOImpl;
import com.poly.entity.Log;
import com.poly.entity.User;

public class AppFilter implements Filter {
    private final LogDAOImpl logDAO = new LogDAOImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // 1. Thiết lập UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        
        String username = null;
        
        // Kiểm tra session và người dùng đã đăng nhập
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            username = user.getUsername();
        }
        
        // 2. Ghi Log truy cập (nếu đã đăng nhập)
        if (username != null) {
            String uri = req.getRequestURI();
            Log log = new Log(uri, new Date(), username);
            logDAO.create(log);
        }

        chain.doFilter(request, response);
    }
    
    @Override public void init(FilterConfig filterConfig) throws ServletException {}
    @Override public void destroy() {}
}
