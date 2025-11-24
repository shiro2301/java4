package controller;

import java.io.IOException;

import dao.UserDAO;
import dao.UserDAOImpl; // Đã đổi từ dao.UserDAOImpl thành dao.impl.UserDAOImpl nếu bạn dùng package dao.impl
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAOImpl();

    // ====================================================================================
    // ✅ PHƯƠNG THỨC ĐÃ THÊM: doGet() (Để hiển thị Form)
    // ====================================================================================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Chuyển hướng đến trang JSP chứa form đăng nhập (/views/login.jsp)
        // Đây là fix cho lỗi HTTP 405
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
    
    // ====================================================================================
    // ⚙️ PHƯƠNG THỨC doPost() (Để xử lý logic Đăng nhập)
    // ====================================================================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idOrEmail = request.getParameter("idOrEmail");
        String password = request.getParameter("password");

        // 1. Gọi phương thức tìm kiếm User theo ID hoặc Email (Bài 2)
        User user = userDAO.findByIdOrEmail(idOrEmail);

        if (user != null && user.getPassword().equals(password)) {
            // Đăng nhập thành công
            request.getSession().setAttribute("user", user); 
            request.getSession().setMaxInactiveInterval(3600); 
            
            request.setAttribute("message", "Đăng nhập thành công!");
            response.sendRedirect(request.getContextPath() + "/views/index.jsp"); 
        } else {
            // Đăng nhập thất bại
            request.setAttribute("idOrEmail", idOrEmail);
            request.setAttribute("error", "ID/Email hoặc Mật khẩu không đúng!");
            
            // Dùng forward để giữ lại thông báo lỗi và giá trị idOrEmail
            request.getRequestDispatcher("/views/login.jsp").forward(request, response); 
        }
    }
}