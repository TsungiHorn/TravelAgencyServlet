package com.kolosovskyi.agency.servlet.admin;


import com.kolosovskyi.agency.exception.service.AddTourValidator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddTourServlet", value = "/AddTourServlet")
public class AddTourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isTitleFail = request.getParameter("fail_title")!=null;
        boolean isPersonFail = request.getParameter("fail_person")!=null;
        boolean isStarsFail = request.getParameter("fail_stars")!=null;
        boolean isPriceFail = request.getParameter("fail_price")!=null;
        boolean isHotFail = request.getParameter("fail_hot")!=null;
        boolean isHiddenFail = request.getParameter("fail_hidden")!=null;
        boolean isCountryFail = request.getParameter("fail_country")!=null;
        boolean isCityFail = request.getParameter("fail_city")!=null;
        boolean isDateFail = request.getParameter("fail_date")!=null;
        request.setAttribute("fail_title", isTitleFail);
        request.setAttribute("fail_person", isPersonFail);
        request.setAttribute("fail_stars", isStarsFail);
        request.setAttribute("fail_price", isPriceFail);
        request.setAttribute("fail_hot", isHotFail);
        request.setAttribute("fail_hidden", isHiddenFail);
        request.setAttribute("fail_country", isCountryFail);
        request.setAttribute("fail_city", isCityFail);
        request.setAttribute("fail_date", isDateFail);
        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/add-tour.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddTourValidator.checkingTourValidity(request, response);
//        tourDAO.create(
//                new Tour(request.getParameter("title"),
//                TourType.valueOf(request.getParameter("type")),
//                Long.valueOf(request.getParameter("person")),
//                Integer.parseInt(request.getParameter("stars")),
//                BigDecimal.valueOf(Long.parseLong(request.getParameter("price"))),
//                Boolean.valueOf(request.getParameter("hot")),
//                Boolean.valueOf(request.getParameter("hidden")),
//                request.getParameter("country"),
//                request.getParameter("city"),
//                LocalDate.parse(request.getParameter("date")))
//        );
//        response.sendRedirect("/4admin-catalog");
    }
}
