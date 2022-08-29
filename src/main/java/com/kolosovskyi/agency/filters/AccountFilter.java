package com.kolosovskyi.agency.filters;

import com.kolosovskyi.agency.dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(filterName = "AccountFilter", urlPatterns = {"/booking/*", "/profile/*", "/4admin-users/*",
        "/AddTourServlet/*", "/BlockUserServlet*", "/4admin-catalog/*", "/DeleteTourServlet/*",
        "/delete-user/*", "/edit-tour*"})

public class AccountFilter implements Filter {
private final UserDAO userDAO = UserDAO.getInstance();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        String email = (String) session.getAttribute("email");
        boolean isAuthorized = email!=null && userDAO.isExistingCreateAccount(email);
        if(isAuthorized){
            chain.doFilter(request, response);
        }else {
            httpResp.sendRedirect("/login");
        }
    }
}
