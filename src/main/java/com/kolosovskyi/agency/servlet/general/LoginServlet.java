package com.kolosovskyi.agency.servlet.general;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import com.kolosovskyi.agency.service.PasswordHasher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = UserDAO.getInstance();
    private final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        User user = userDAO.getUserByEmail(email).orElse(new User());
        String password = null;
        try {
            password = PasswordHasher.toHexString(PasswordHasher.getSHA(request.getParameter("password")));
        } catch (NoSuchAlgorithmException e) {
            logger.error("cannot hash password", e);
        }

        if (userDAO.isExistingLogin(email, password)) {
            if (Boolean.TRUE.equals(user.getBlocked())) {
                response.sendRedirect("/BlockedUserServlet");
            } else {
                redirectByRole(request, response, email, user);
            }
        } else {
            response.sendRedirect("/login?fail");
        }
    }

    private void redirectByRole(HttpServletRequest request, HttpServletResponse response, String email, User user) throws IOException {
        if (user.getRole() == Role.USER) {
            request.getSession().setAttribute("email", email);
            response.sendRedirect("/profile?email=" + email);
        } else if (user.getRole() == Role.ADMIN) {
            request.getSession().setAttribute("email", email);
            response.sendRedirect("/4admin-catalog");
        }else if(user.getRole() == Role.MANAGER){
            request.getSession().setAttribute("email", email);
            response.sendRedirect("/manager-users");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isFail = request.getParameter("fail") != null;
        request.setAttribute("fail", isFail);

        RequestDispatcher rd = request.getRequestDispatcher("/view/login.jsp");
        rd.forward(request, response);
    }
}
