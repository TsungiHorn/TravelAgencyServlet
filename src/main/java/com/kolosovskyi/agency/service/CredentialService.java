package com.kolosovskyi.agency.service;

import com.kolosovskyi.agency.dao.UserDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialService {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final UserDAO userDAO= UserDAO.getInstance();
    private CredentialService(){pattern = Pattern.compile(EMAIL_PATTERN);}
    private static class Instance{
        private static final CredentialService CREDENTIAL_SERVICE = new CredentialService();
    }

    public static CredentialService getInstance(){
        return Instance.CREDENTIAL_SERVICE;
    }

    public boolean isCredentialFree(String name, String email){
    return email != null && name != null && !email.isEmpty() &&
        !userDAO.isExistingCreateAccount(email)
        && !name.isEmpty();
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public boolean isCorrectPassword(String password){
        return password.length() >= 8 && !password.isBlank();
    }
}
