package com.kolosovskyi.agency.servlet.manager;

import com.kolosovskyi.agency.dao.UserToursDAO;
import com.kolosovskyi.agency.entity.TourStatus;
import com.kolosovskyi.agency.entity.UserTours;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "StatusServlet", value = "/manager-edit-status")
public class StatusServlet extends HttpServlet {
    private final UserToursDAO userToursDAO = UserToursDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserTours userTours = userToursDAO.readTourUser(
                        Long.valueOf(request.getParameter("ui")),
                        Long.valueOf(request.getParameter("ti")))
                .orElse(new UserTours());
        String action = request.getParameter("act");
        switch (action) {
            case "reg":
                userTours.setStatus(TourStatus.REGISTERED);
                break;
            case "pay":
                userTours.setStatus(TourStatus.PAID);
                break;
            case "can":
                userTours.setStatus(TourStatus.CANCELED);
                break;
        }
        userToursDAO.update(userTours);
        response.sendRedirect("/manager-users");
    }
}
