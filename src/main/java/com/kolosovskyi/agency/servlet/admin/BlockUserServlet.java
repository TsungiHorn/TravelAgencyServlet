package com.kolosovskyi.agency.servlet.admin;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "BlockUserServlet", value = "/BlockUserServlet")
public class BlockUserServlet extends HttpServlet {
      private  final UserDAO userDAO = UserDAO.getInstance();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User user = userDAO.read(Long.valueOf(request.getParameter("i"))).orElse(new User());
    user.setBlocked(!user.getBlocked());
    userDAO.update(user);
    response.sendRedirect("/4admin-users");
    }
}
