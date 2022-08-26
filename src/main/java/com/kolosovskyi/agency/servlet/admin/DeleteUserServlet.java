package com.kolosovskyi.agency.servlet.admin;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", value = "/delete-user")
public class DeleteUserServlet extends HttpServlet {
    private final UserDAO userDAO = UserDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDAO.read(Long.valueOf(request.getParameter("id"))).orElse(new User());
        userDAO.delete(user);
        response.sendRedirect("/4admin-users");
    }
}
