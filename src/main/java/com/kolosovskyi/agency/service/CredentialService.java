package com.kolosovskyi.agency.service;

import com.kolosovskyi.agency.dao.UserDAO;

public class CredentialService {
    private static final UserDAO userDAO= UserDAO.getUserDAO();
    private CredentialService(){}
    private static class Instance{
        private static final CredentialService CREDENTIAL_SERVICE = new CredentialService();
    }

    public static CredentialService getInstance(){
        return Instance.CREDENTIAL_SERVICE;
    }

    public boolean isRightMember(String name, String email, String password){
    return !email.isEmpty() &&
        !userDAO.isExistingCreateAccount(email)
        && !name.isEmpty()
        && password.length() >= 8;
    }
}
