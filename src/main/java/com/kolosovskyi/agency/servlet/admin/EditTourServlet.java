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

@WebServlet(name = "EditTourServlet", value = "/edit-tour")
public class EditTourServlet extends HttpServlet {
    private static final TourDAO TOUR_DAO = TourDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tour tour = TOUR_DAO.read(Long.valueOf(request.getParameter("i"))).orElse(new Tour());
        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/edit-tour.jsp");
        request.setAttribute("tour", tour);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tour tour = TOUR_DAO.read(Long.valueOf(request.getParameter("id"))).orElse(new Tour());

            tour.setTitle(request.getParameter("title"));
            tour.setTourType(TourType.values()[Integer.parseInt(request.getParameter("type"))]);
            tour.setPersonNumber(Long.valueOf(request.getParameter("person")));
            tour.setHotelStars(Integer.parseInt(request.getParameter("stars")));
            tour.setPrice(BigDecimal.valueOf(Long.parseLong(request.getParameter("price"))));
            tour.setCountry(request.getParameter("country"));
            tour.setCity(request.getParameter("city"));
            tour.setStartDate(LocalDate.parse(request.getParameter("date")));
            tour.setHot(Boolean.valueOf(request.getParameter("hot")));
            tour.setHidden(Boolean.valueOf(request.getParameter("hidden")));

        TOUR_DAO.update(tour);
        response.sendRedirect("/4admin-catalog");
    }
}
