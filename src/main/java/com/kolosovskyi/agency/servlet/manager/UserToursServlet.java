package com.kolosovskyi.agency.servlet.manager;

import com.kolosovskyi.agency.dao.UserToursDAO;
import com.kolosovskyi.agency.entity.UserTours;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/manager-users")
public class UserToursServlet extends HttpServlet {
    private final UserToursDAO userToursDAO = UserToursDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserTours> orders = userToursDAO.getAll();
        request.setAttribute("orders", orders);
        RequestDispatcher rd = request.getRequestDispatcher("/view/manager/users.jsp");
        rd.forward(request, response);
    }
}

