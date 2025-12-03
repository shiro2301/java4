package com.poly.servlet;


import com.poly.dao.UserDaoImpl;
import com.poly.dao.UserDAO;
import com.poly.entity.User;
import com.poly.filter.AuthFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO dao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        if (session != null) {
            // Lấy thông báo lỗi từ Session (nếu có lỗi AuthFilter chuyển hướng)
            String authError = (String) session.getAttribute(AuthFilter.AUTH_ERROR);
            if (authError != null) {
                req.setAttribute("authError", authError); // Chuyển sang Request Scope để hiển thị
                session.removeAttribute(AuthFilter.AUTH_ERROR); // Xóa khỏi Session
            }
        }
        
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        User user = dao.findById(username);
        String message = "";

        if (user == null) {
            message = "Invalid username";
        } else if (!user.getPassword().equals(password)) {
            message = "Invalid password";
        } else {
            // Đăng nhập thành công
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            message = "Login successfully";

            // Kiểm tra SECURITY_URI và chuyển hướng
            String securityUri = (String) session.getAttribute(AuthFilter.SECURITY_URI);
            
            if (securityUri != null) {
                resp.sendRedirect(securityUri); // Quay lại trang bị chặn
                return;
            }
        }
        
        req.setAttribute("message", message);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
