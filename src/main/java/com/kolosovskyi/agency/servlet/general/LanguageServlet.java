package com.kolosovskyi.agency.servlet.general;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LanguageServlet", value = "/changing-lang")
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        session.setAttribute("lang", lang);
        response.sendRedirect(request.getHeader("referer"));
    }
}
