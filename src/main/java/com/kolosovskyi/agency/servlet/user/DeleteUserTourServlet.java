package com.kolosovskyi.agency.servlet.user;

import com.kolosovskyi.agency.dao.UserToursDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteTourServlet", value = "/deleteTour")
public class DeleteUserTourServlet extends HttpServlet {
    private static final UserToursDAO USER_TOURS_DAO = UserToursDAO.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        USER_TOURS_DAO.delete(Long.valueOf(request.getParameter("ui")), Long.valueOf(request.getParameter("ti")));
        response.sendRedirect("/profile");
    }
}
