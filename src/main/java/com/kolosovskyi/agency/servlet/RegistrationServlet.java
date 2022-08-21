package com.kolosovskyi.agency.servlet;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import com.kolosovskyi.agency.service.CredentialService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final CredentialService CREDENTIAL_SERVICE = CredentialService.getInstance();
    private static final UserDAO USER_DAO = UserDAO.getInstance();  //serializable

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (CREDENTIAL_SERVICE.isRightMember(name, email, password)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(Role.USER);
            user.setBlocked(false);
            USER_DAO.create(user);
            response.sendRedirect("/login");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/view/registration.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/view/registration.jsp");
        rd.forward(request, response);
    }
}
