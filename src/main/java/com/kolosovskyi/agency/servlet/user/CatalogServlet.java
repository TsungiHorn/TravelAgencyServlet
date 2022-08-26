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
    private final TourDAO tourDAO = TourDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("order-by-price")!=null){
            List<Tour> tours = tourDAO.getTourOrderByPrice();
            RequestDispatcher rd = request.getRequestDispatcher("/view/user/catalog.jsp");
            request.setAttribute("tours", tours);
            rd.forward(request, response);
        }else {
            List<Tour> hotTours = tourDAO.getHotTours();
            List<Tour> simpleTours = tourDAO.getSimpleTours();
            RequestDispatcher rd = request.getRequestDispatcher("/view/user/catalog.jsp");
            request.setAttribute("hotTours", hotTours);
            request.setAttribute("simpleTours", simpleTours);
            rd.forward(request, response);
        }
    }
}
