package com.kolosovskyi.agency.servlet.user;

import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.entity.Tour;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Catalog", value = "/catalog")
public class CatalogServlet extends HttpServlet {
    private static final TourDAO TOUR_DAO = TourDAO.getInstance();
    private static final String PATH_TO_CATALOG = "/view/user/catalog.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tour> tours = filterBy(request);
        request.setAttribute("tours", tours);
        RequestDispatcher rd = request.getRequestDispatcher(PATH_TO_CATALOG);
        rd.forward(request, response);
    }




    private static List<Tour> filterBy(HttpServletRequest request) {
        String filterName = request.getParameter("filterName");
        int page = 1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<Tour> tours = new ArrayList<>();
        if (request.getParameter("filterName") == null) {
            tours = TOUR_DAO.getAll((page - 1) * 9);
            int noOfPages = TOUR_DAO.getAmountOfPages();
            request.setAttribute("tours", tours);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            return tours;
        } else if (filterName.equals("price")) {
            tours = TOUR_DAO.getTourOrderByPrice((page - 1) * 9);
            int noOfPages = TOUR_DAO.getAmountOfPages();
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("byPrice", tours);
            return tours;

        } else if (filterName.equals("stars")) {
            tours = TOUR_DAO.getTourOrderByStars((page - 1) * 9);
            int noOfPages = TOUR_DAO.getAmountOfPages();
            request.setAttribute("byStars", tours);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            return tours;
        } else if (filterName.equals("people")) {
            tours = TOUR_DAO.getTourOrderByCountOfPerson((page - 1) * 9);
            int noOfPages = TOUR_DAO.getAmountOfPages();
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("byPeople", tours);
            return tours;
        }
        return tours;
    }
}
