package com.kolosovskyi.agency.servlet;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.dao.UserToursDAO;
import com.kolosovskyi.agency.entity.TourStatus;
import com.kolosovskyi.agency.entity.User;
import com.kolosovskyi.agency.entity.UserTours;
import com.kolosovskyi.agency.service.DateEqualsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    private static final UserDAO USER_DAO = UserDAO.getUserDAO();
    private static final UserToursDAO USER_TOURS_DAO = UserToursDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = USER_DAO.getUserByEmail((String)request.getSession().getAttribute("email")).orElse(new User());
        request.setAttribute("user", user);
        List<UserTours> userTours = USER_TOURS_DAO.read(user);
        for (UserTours userTour: userTours) {
            if(DateEqualsService.getInstance().isCanceled(userTour.getOrderTime(), LocalDate.now()))
                userTour.setStatus(TourStatus.CANCELED);
        }
        request.setAttribute("userTours", userTours);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/profile.jsp");
        requestDispatcher.forward(request, response);
    }
}
