package controller;

import dao.ShareDAOImpl;
import dto.ReportShareDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/report/share")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShareDAOImpl shareDAO = new ShareDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Gọi phương thức tạo báo cáo
        List<ReportShareDTO> reportList = shareDAO.getShareReport();

        // 2. Gửi danh sách DTO về JSP
        request.setAttribute("reportList", reportList);
        
        request.getRequestDispatcher("/views/share-report.jsp").forward(request, response);
    }
}