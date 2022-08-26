package com.kolosovskyi.agency.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(filterName = "AccountFilter", urlPatterns = {"/booking/*", "/profile/*"})

public class AccountFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();

        boolean isAuthorized = session.getAttribute("email")!=null;
        if(isAuthorized){
            chain.doFilter(request, response);
        }else {
            httpResp.sendRedirect("/login");
        }
    }
}
