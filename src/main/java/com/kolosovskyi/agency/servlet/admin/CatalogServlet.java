package com.kolosovskyi.agency.servlet.admin;

import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.entity.Tour;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCatalogServlet", value = "/4admin-catalog")
public class CatalogServlet extends HttpServlet {
    private static final TourDAO TOUR_DAO = TourDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tour> tours = TOUR_DAO.getAll();
        request.setAttribute("tours", tours);
        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/admin-tours.jsp");
        rd.forward(request, response);
    }
}
