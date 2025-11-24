package controller;

import dao.VideoDAO;
import dao.VideoDAOImpl;
import entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/video/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VideoDAO videoDAO = new VideoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String keyword = request.getParameter("keyword");

        if (keyword != null && !keyword.trim().isEmpty()) {
            // Chuẩn bị từ khóa cho JPQL LIKE
            String searchPattern = "%" + keyword.trim() + "%";
            
            // Thực hiện tìm kiếm video theo tiêu đề
            List<Video> videos = videoDAO.findByTitleKeyword(searchPattern);

            // Gửi dữ liệu và từ khóa về JSP
            request.setAttribute("keyword", keyword);
            request.setAttribute("videoList", videos);
        }
        
        request.getRequestDispatcher("/views/search-results.jsp").forward(request, response);
    }
}