package com.poly.filter;

import com.poly.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// Lọc các URL yêu cầu đăng nhập hoặc phân quyền admin
@WebFilter({
    "/admin/*",
    "/account/change-password",
    "/account/edit-profile",
    "/video/like/*",
    "/video/share/*"
})
public class AuthFilter implements Filter {
	public static final String SECURITY_URI = "securityUri"; 
    public static final String AUTH_ERROR = "authError"; // <--- Thêm key mới
	private static final String AUTH_URI = null;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String uri = req.getRequestURI();

        // Biến lưu trữ thông báo lỗi
        String errorMessage = null;

        if (user == null) {
            // Trường hợp 1: CHƯA ĐĂNG NHẬP
            errorMessage = "Vui lòng đăng nhập để truy cập trang này.";
        } else if (uri.contains("/admin/") && !user.getAdmin()) {
            // Trường hợp 2: ĐÃ ĐĂNG NHẬP NHƯNG KHÔNG PHẢI ADMIN
            errorMessage = "Bạn cần là Quản trị viên để truy cập trang này.";
        }

        if (errorMessage != null) {
            // Lưu URL bị chặn và thông báo lỗi vào Session
            session.setAttribute(AUTH_ERROR, errorMessage); 
            session.setAttribute(SECURITY_URI, uri); 
            
            // Chuyển hướng đến trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login"); 
            return;
        } else {
            // Đã đăng nhập và có quyền
            session.removeAttribute(AUTH_URI); 
            session.removeAttribute(AUTH_ERROR); // Xóa lỗi nếu đã có
            chain.doFilter(request, response);
        }
    }
    // ... (init và destroy)
}