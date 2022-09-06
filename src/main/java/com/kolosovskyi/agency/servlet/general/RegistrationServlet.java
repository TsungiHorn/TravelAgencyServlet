package com.kolosovskyi.agency.servlet.general;

import com.kolosovskyi.agency.exception.service.CredentialService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final CredentialService credentialService = CredentialService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        credentialService.registrationChecked(response, name, password, email);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isPasswordFail = request.getParameter("password_fail") != null;
        boolean isEmailFail = request.getParameter("email_fail") != null;
        request.setAttribute("password_fail", isPasswordFail);
        request.setAttribute("email_fail", isEmailFail);
        RequestDispatcher rd = request.getRequestDispatcher("/view/registration.jsp");
        rd.forward(request, response);
    }
}
