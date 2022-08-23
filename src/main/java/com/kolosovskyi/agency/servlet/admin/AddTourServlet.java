package com.kolosovskyi.agency.servlet.admin;

import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.entity.Tour;
import com.kolosovskyi.agency.entity.TourType;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet(name = "AddTourServlet", value = "/AddTourServlet")
public class AddTourServlet extends HttpServlet {
    private static final TourDAO TOUR_DAO = TourDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/add-tour.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TOUR_DAO.create(new Tour(request.getParameter("title"),
                TourType.values()[Integer.parseInt(request.getParameter("type"))],
                Long.valueOf(request.getParameter("person")),
                Integer.parseInt(request.getParameter("stars")),
                BigDecimal.valueOf(Long.parseLong(request.getParameter("price"))),
                Boolean.valueOf(request.getParameter("hot")),
                Boolean.valueOf(request.getParameter("hidden")),
                request.getParameter("country"),
                request.getParameter("city"),
                LocalDate.parse(request.getParameter("date"))));
        response.sendRedirect("/4admin-main");
    }
}
