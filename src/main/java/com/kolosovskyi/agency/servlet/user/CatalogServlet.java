package com.kolosovskyi.agency.servlet.user;

import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.entity.Tour;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Catalog", value = "/catalog")
public class CatalogServlet extends HttpServlet {
    private static final TourDAO TOUR_DAO = TourDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Tour> hotTours = TOUR_DAO.getHot();
        List<Tour> simpleTours = TOUR_DAO.getSimple();
        RequestDispatcher rd = request.getRequestDispatcher("/view/user/catalog.jsp");
        request.setAttribute("hotTours", hotTours);
        request.setAttribute("simpleTours", simpleTours);
        rd.forward(request, response);
    }
}
