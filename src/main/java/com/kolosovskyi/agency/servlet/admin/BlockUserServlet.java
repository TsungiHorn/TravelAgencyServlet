package com.kolosovskyi.agency.servlet.admin;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BlockUserServlet", value = "/BlockUserServlet")
public class BlockUserServlet extends HttpServlet {
      private static final UserDAO USER_DAO = UserDAO.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User user = USER_DAO.read(Long.valueOf(request.getParameter("i"))).orElse(new User());
    user.setBlocked(!user.getBlocked());
    USER_DAO.update(user);
    response.sendRedirect("/4admin-users");
    }
}
