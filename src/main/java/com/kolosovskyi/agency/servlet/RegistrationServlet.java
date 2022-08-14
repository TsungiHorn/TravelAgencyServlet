package com.kolosovskyi.agency.servlet;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/view/registration.jsp");
        rd.forward(request, response);

        String name = request.getParameter("name");
        User user = new User();
        user.setName(name);
        UserDAO.getUserDAO().create(user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
