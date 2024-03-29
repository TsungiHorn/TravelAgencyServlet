package com.kolosovskyi.agency.servlet.admin;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUsersServlet", value = "/4admin-users")
public class UsersServlet extends HttpServlet {
    private  final UserDAO userDAO = UserDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAll();
        request.setAttribute("users", users);
        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/users.jsp");
        rd.forward(request, response);
    }
}
