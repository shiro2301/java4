package com.poly.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout") // Ánh xạ tới URL /logout
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Xóa thuộc tính 'user' khỏi session
            session.removeAttribute("user"); 
            
            // Tùy chọn: Hủy toàn bộ session (sẽ tạo session mới cho lần truy cập tiếp theo)
            // session.invalidate(); 
        }
        
        // Chuyển hướng người dùng về trang chủ
        response.sendRedirect(request.getContextPath() + "/account/sign-up"); 
        // Hoặc về trang chung: response.sendRedirect(request.getContextPath() + "/page.jsp");
    }
}