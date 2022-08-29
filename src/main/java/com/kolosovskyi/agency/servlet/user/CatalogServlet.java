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
    private static final String PATH_TO_CATALOG = "/view/user/catalog.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("by-price") != null) {
            List<Tour> toursByPrice = TOUR_DAO.getTourOrderByPrice();
            RequestDispatcher rd = request.getRequestDispatcher(PATH_TO_CATALOG);
            request.setAttribute("byPrice", toursByPrice);
            rd.forward(request, response);
        } else if (request.getParameter("by-stars") != null) {
            List<Tour> toursByStars = TOUR_DAO.getTourOrderByStars();
            RequestDispatcher rd = request.getRequestDispatcher(PATH_TO_CATALOG);
            request.setAttribute("byStars", toursByStars);
            rd.forward(request, response);
        } else if (request.getParameter("by-people") != null) {
            List<Tour> toursByCountOfPerson = TOUR_DAO.getTourOrderByCountOfPerson();
            RequestDispatcher rd = request.getRequestDispatcher(PATH_TO_CATALOG);
            request.setAttribute("byPeople", toursByCountOfPerson);
            rd.forward(request, response);
        } else {
            List<Tour> hotTours = TOUR_DAO.getHotTours();
            List<Tour> simpleTours = TOUR_DAO.getSimpleTours();
            RequestDispatcher rd = request.getRequestDispatcher(PATH_TO_CATALOG);
            request.setAttribute("hotTours", hotTours);
            request.setAttribute("simpleTours", simpleTours);
            rd.forward(request, response);
        }
    }

    private void setFiltersByPrice(String filterNameByPrice){
        if(filterNameByPrice!=null){

        }
    }
}
