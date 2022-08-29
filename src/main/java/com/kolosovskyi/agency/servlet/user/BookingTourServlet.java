package com.kolosovskyi.agency.servlet.user;

import com.kolosovskyi.agency.dao.TourDAO;
import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.dao.UserToursDAO;
import com.kolosovskyi.agency.entity.Tour;
import com.kolosovskyi.agency.entity.TourStatus;
import com.kolosovskyi.agency.entity.User;
import com.kolosovskyi.agency.entity.UserTours;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "BookingTourServlet", value = "/booking")
public class BookingTourServlet extends HttpServlet {
    private static final UserDAO USER_DAO = UserDAO.getInstance();
    private static final UserToursDAO USER_TOURS_DAO = UserToursDAO.getInstance();
    private static final TourDAO TOUR_DAO = TourDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        User user = USER_DAO.getUserByEmail(email).orElse(new User());
        Tour tour = TOUR_DAO.read(Long.valueOf(request.getParameter("q"))).orElse(new Tour());
        UserTours userTours = new UserTours(user, tour, LocalDate.now(), TourStatus.REGISTERED,
                tour.getPrice(), 0);
        bookTourOperation(user, tour, userTours);
        response.sendRedirect("/profile");
    }

    private void bookTourOperation(User user, Tour tour, UserTours userTours) {
        if(USER_TOURS_DAO.readTourUser(user.getId(), tour.getId()).isPresent()){
            if(USER_TOURS_DAO.readTourUser(user.getId(), tour.getId()).get().getStatus()!=TourStatus.PAID)
                USER_TOURS_DAO.update(userTours);
        }else {
            USER_TOURS_DAO.create(userTours);
        }
    }
}
