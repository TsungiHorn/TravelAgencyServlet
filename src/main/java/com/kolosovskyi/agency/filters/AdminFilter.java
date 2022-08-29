package com.kolosovskyi.agency.filters;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/4admin-users/*",
        "/AddTourServlet/*", "/BlockUserServlet*", "/4admin-catalog/*", "/DeleteTourServlet/*",
        "/delete-user/*", "/edit-tour*"})
public class AdminFilter implements Filter {
    private final UserDAO userDAO = UserDAO.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        String email = (String) session.getAttribute("email");
        User user = userDAO.getUserByEmail(email).orElse(new User());
        boolean isAuthorized = email != null && userDAO.isExistingCreateAccount(email);
        if (isAuthorized && user.getRole() == Role.ADMIN) {
            chain.doFilter(request, response);
        } else {
            httpResp.sendRedirect("/login");
        }
    }
}
