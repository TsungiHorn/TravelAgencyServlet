package com.kolosovskyi.agency.servlet.manager;

import com.kolosovskyi.agency.dao.UserToursDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteUserTourServlet", value = "/manager-delete-order")
public class DeleteUserTourServlet extends HttpServlet {
    private static final UserToursDAO USER_TOURS_DAO= UserToursDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userID = Long.valueOf(request.getParameter("ui"));
        Long tourID = Long.valueOf(request.getParameter("ti"));
        USER_TOURS_DAO.delete(userID, tourID);
        response.sendRedirect("/manager-users");
    }
}
