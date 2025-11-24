package com.poly.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import com.poly.dao.UserDAO;
import com.poly.dao.impl.UserDAOImpl;
import com.poly.entity.Favorite;
import com.poly.entity.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/favorite/list/*")
public class FavoriteListServlet extends HttpServlet {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        String userId = req.getPathInfo().substring(1);
        // Tìm User
        User user = userDAO.findById(userId);

        if (user == null) {
            req.setAttribute("message", "User không tồn tại!");
        } else {
            req.setAttribute("user", user);

            List<Favorite> favorites = user.getFavorites();
            req.setAttribute("favorites", favorites);
        }

        req.getRequestDispatcher("/pages/favorite-list.jsp").forward(req, resp);
    }
}
