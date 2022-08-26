package com.kolosovskyi.agency.servlet.user;

import com.kolosovskyi.agency.dao.UserToursDAO;
import com.kolosovskyi.agency.entity.TourStatus;
import com.kolosovskyi.agency.entity.UserTours;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BuyTourServlet", value = "/buy")
public class BuyTourServlet extends HttpServlet {
    private  final UserToursDAO userToursDAO = UserToursDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserTours userTour = userToursDAO.readTourUser(Long.valueOf(request.getParameter("ui")),
                Long.valueOf(request.getParameter("ti"))).orElse(new UserTours());
        if (userTour.getStatus() != TourStatus.PAID && userTour.getStatus()!=TourStatus.CANCELED) {
            userTour.setStatus(TourStatus.PAID);
            userToursDAO.update(userTour);
        }
        response.sendRedirect("/profile");
    }
}
