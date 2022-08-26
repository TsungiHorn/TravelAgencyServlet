package com.kolosovskyi.agency.servlet.user;

import com.kolosovskyi.agency.dao.UserToursDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteTourServlet", value = "/deleteTour")
public class DeleteUserTourServlet extends HttpServlet {
    private final UserToursDAO userToursDAO = UserToursDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userToursDAO.delete(Long.valueOf(request.getParameter("ui")), Long.valueOf(request.getParameter("ti")));
        response.sendRedirect("/profile");
    }
}
