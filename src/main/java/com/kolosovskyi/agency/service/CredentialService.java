package com.kolosovskyi.agency.service;

import com.kolosovskyi.agency.dao.UserDAO;

public class CredentialService {
    private static final UserDAO userDAO= UserDAO.getInstance();
    private CredentialService(){}
    private static class Instance{
        private static final CredentialService CREDENTIAL_SERVICE = new CredentialService();
    }

    public static CredentialService getInstance(){
        return Instance.CREDENTIAL_SERVICE;
    }

    public boolean isCredentialValid(String name, String email){
    return email != null && name != null && !email.isEmpty() &&
        !userDAO.isExistingCreateAccount(email)
        && !name.isEmpty();
    }
}
