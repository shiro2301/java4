package com.poly.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import com.poly.dao.FavoriteDAO;
import com.poly.dao.impl.FavoriteDAOImpl;
import com.poly.entity.Favorite;

import java.io.IOException;
import java.util.List;

@WebServlet("/favorite/list")
public class FavoriteListAllServlet extends HttpServlet {

    FavoriteDAO favDao = new FavoriteDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        List<Favorite> favorites = favDao.findAll();

        req.setAttribute("favorites", favorites);

        req.getRequestDispatcher("/pages/favorite-list.jsp").forward(req, resp);
    }
}
