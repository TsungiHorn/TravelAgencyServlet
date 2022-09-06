package com.kolosovskyi.agency.exception.service;

import com.kolosovskyi.agency.dao.UserDAO;
import com.kolosovskyi.agency.entity.Role;
import com.kolosovskyi.agency.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialService {
    private final Pattern pattern;
    private static final Service isPasswordValid = x -> x.length() >= 8 && !x.isBlank();
    private final Logger logger = LoggerFactory.getLogger(CredentialService.class);
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final UserDAO userDAO = UserDAO.getInstance();

    private CredentialService() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    private static class Instance {
        private static final CredentialService CREDENTIAL_SERVICE = new CredentialService();
    }

    public static CredentialService getInstance() {
        return Instance.CREDENTIAL_SERVICE;
    }

    public boolean isCredentialFree(String name, String email) {
        return email != null && name != null && !email.isEmpty() &&
                !userDAO.isExistingCreateAccount(email)
                && !name.isEmpty();
    }

    public boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isCorrectPassword(String password) {
        return password.length() >= 8 && !password.isBlank();
    }

    public void registrationChecked(HttpServletResponse response, String name, String password, String email) throws IOException {
        if (validateEmail(email) && isCredentialFree(name, email)) {
            if (isPasswordValid.isValid(password)) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                try {
                    password = PasswordHasher.toHexString(PasswordHasher.getSHA(password));
                } catch (NoSuchAlgorithmException e) {
                    logger.error("cannot hash password", e);
                }
                user.setPassword(password);
                user.setRole(Role.USER);
                user.setBlocked(false);
                userDAO.create(user);
                response.sendRedirect("/login");
            } else {
                response.sendRedirect("/registration?password_fail");
            }
        } else {
            response.sendRedirect("/registration?email_fail");
        }
    }
}
