package com.kolosovskyi.agency.servlet;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
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
        String email = request.getParameter("email");
        User user = USER_DAO.getUserByEmail(email).orElse(new User());
        if (Boolean.TRUE.equals(user.getBlocked())) {
            response.sendRedirect("/BlockedUserServlet");
        } else {
            if (USER_DAO.isExistingLogin(email, request.getParameter("password"))) {
                if (user.getRole() == Role.USER) {
                    request.getSession().setAttribute("email", email);
                    response.sendRedirect("/profile?email=" + email);
                } else if (user.getRole() == Role.ADMIN) {
                    request.getSession().setAttribute("email", email);
                    response.sendRedirect("/4admin-catalog");
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/view/login.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/view/login.jsp");
        rd.forward(request, response);
    }
}
