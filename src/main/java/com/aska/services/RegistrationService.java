package com.aska.services;

import com.aska.forms.RegistrationForm;

import java.sql.SQLException;

public interface RegistrationService {

    boolean isExist(String email) throws SQLException;

    void registerUser(RegistrationForm registrationForm) throws SQLException;

}
