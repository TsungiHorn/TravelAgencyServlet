package com.kolosovskyi.agency.servlet.admin;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditRoleServlet", value = "/edit-role")
public class EditRoleServlet extends HttpServlet {
    private final UserDAO userDAO = UserDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDAO.read(Long.valueOf(request.getParameter("id"))).orElse(new User());
        request.setAttribute("user", user);
        RequestDispatcher rd = request.getRequestDispatcher("/view/admin/edit-role.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDAO.read(Long.valueOf(request.getParameter("id"))).orElse(new User());
        user.setRole(Role.valueOf(request.getParameter("role")));
        userDAO.update(user);
        response.sendRedirect("/4admin-users");
    }
}
