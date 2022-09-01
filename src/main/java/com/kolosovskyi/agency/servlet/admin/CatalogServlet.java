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
    private final TourDAO tourDAO = TourDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<Tour> tours = tourDAO.getAllToursToADMIN((page-1) * 6);


        int noOfPages = tourDAO.getAmountOfPages();

        request.setAttribute("tours", tours);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/admin-tours.jsp");
        rd.forward(request, response);
    }
}