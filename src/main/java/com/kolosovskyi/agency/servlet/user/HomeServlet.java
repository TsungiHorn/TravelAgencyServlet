package com.kolosovskyi.agency.servlet.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Home", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("lang")==null)
            request.getSession().setAttribute("lang", "en");
        RequestDispatcher rd = request.getRequestDispatcher("/view/user/home.jsp");
        rd.forward(request, response);
    }
}
