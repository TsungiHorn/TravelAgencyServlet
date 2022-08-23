package com.kolosovskyi.agency.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BlockedUserServlet", value = "/BlockedUserServlet")
public class BlockedUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/view/blocked_user_warning.jsp");
        rd.forward(request, response);
    }
}
