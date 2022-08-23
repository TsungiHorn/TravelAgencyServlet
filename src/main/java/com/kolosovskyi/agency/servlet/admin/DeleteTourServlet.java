package com.kolosovskyi.agency.servlet.admin;

import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.entity.Tour;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletDeleteTourServlet", value = "/DeleteTourServlet")
public class DeleteTourServlet extends HttpServlet {
    private static final TourDAO TOUR_DAO = TourDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tour tour = TOUR_DAO.read(Long.valueOf(request.getParameter("i"))).orElse(new Tour());
        TOUR_DAO.delete(tour);
        response.sendRedirect("/4admin-catalog");
    }
}
