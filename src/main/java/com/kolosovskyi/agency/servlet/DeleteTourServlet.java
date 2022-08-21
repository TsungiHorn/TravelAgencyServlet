package com.kolosovskyi.agency.servlet;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.dao.UserToursDAO;
import com.kolosovskyi.agency.entity.UserTours;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteTourServlet", value = "/deleteTour")
public class DeleteTourServlet extends HttpServlet {
    private static final UserToursDAO USER_TOURS_DAO = UserToursDAO.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        USER_TOURS_DAO.delete(Long.valueOf(request.getParameter("ui")), Long.valueOf(request.getParameter("ti")));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        response.sendRedirect("/profile");
    }
}
