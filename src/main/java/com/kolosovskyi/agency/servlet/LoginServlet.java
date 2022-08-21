package com.kolosovskyi.agency.servlet;

import com.kolosovskyi.agency.dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final UserDAO USER_DAO = UserDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (USER_DAO.isExistingLogin(request.getParameter("email"), request.getParameter("password"))) {
            request.getSession().setAttribute("email", request.getParameter("email"));
            response.sendRedirect("/profile?email="+request.getParameter("email"));
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/view/login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/view/login.jsp");
        rd.forward(request, response);
    }
}
