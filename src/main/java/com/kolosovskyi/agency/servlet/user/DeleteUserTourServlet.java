package com.kolosovskyi.agency.servlet.user;

import com.kolosovskyi.agency.dao.UserToursDAO;
import com.kolosovskyi.agency.entity.TourStatus;
import com.kolosovskyi.agency.entity.UserTours;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteTourServlet", value = "/deleteTour")
public class DeleteUserTourServlet extends HttpServlet {
    private final UserToursDAO userToursDAO = UserToursDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserTours userTour = userToursDAO.readTourUser(Long.valueOf(request.getParameter("ui")),
                Long.valueOf(request.getParameter("ti"))).orElse(new UserTours());
        userTour.setStatus(TourStatus.CANCELED);
        userToursDAO.update(userTour);
        response.sendRedirect("/profile");
    }
}
