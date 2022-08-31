package com.kolosovskyi.agency.servlet.general;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import com.kolosovskyi.agency.service.CredentialService;
import com.kolosovskyi.agency.service.PasswordHasher;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final CredentialService credentialService = CredentialService.getInstance();
    private final UserDAO userDAO = UserDAO.getInstance();
    private final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            password = PasswordHasher.toHexString(PasswordHasher.getSHA(password));
        } catch (NoSuchAlgorithmException e) {
            logger.error("cannot hash password", e);
        }
        String email = request.getParameter("email");
        if (credentialService.validateEmail(email) && credentialService.isCredentialFree(name, email)) {
            if (credentialService.isCorrectPassword(password)) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(Role.USER);
                user.setBlocked(false);
                userDAO.create(user);
                response.sendRedirect("/login");
            } else {
                response.sendRedirect("/registration?password_fail");
            }
        } else {
            response.sendRedirect("/registration?email_fail");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isPasswordFail = request.getParameter("password_fail") != null;
        boolean isEmailFail = request.getParameter("email_fail") != null;
        request.setAttribute("password_fail", isPasswordFail);
        request.setAttribute("email_fail", isEmailFail);
        RequestDispatcher rd = request.getRequestDispatcher("/view/registration.jsp");
        rd.forward(request, response);
    }
}
