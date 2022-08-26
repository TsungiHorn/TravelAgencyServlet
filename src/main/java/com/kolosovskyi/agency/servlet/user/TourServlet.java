package com.kolosovskyi.agency.servlet.user;

import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.entity.Tour;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "TourServlet", value = "/tour")
public class TourServlet extends HttpServlet {
    private final TourDAO tourDAO = TourDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("i");
        Tour tour = tourDAO.read(Long.valueOf(parameter)).orElse(new Tour());
        request.setAttribute("tour", tour);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/user/tour.jsp");
        requestDispatcher.forward(request, response);
    }
}
