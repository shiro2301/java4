package com.poly.servlet;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.poly.daoImpl.UserDAOImpl;
import com.poly.entity.User;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAOImpl userDAO = new UserDAOImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Khi nhận yêu cầu GET, chuyển tiếp đến trang JSP để hiển thị form
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = "";

        User user = userDAO.findById(username);
        
        if (user == null) {
            message = "Sai tên đăng nhập!";
        } else {
            if (!user.getPassword().equals(password)) {
                message = "Sai mật khẩu!";
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // Lưu user vào session
                message = "Đăng nhập thành công! Chào mừng, " + user.getHoVaTen();
            }
        }
        
        request.setAttribute("message", message);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
