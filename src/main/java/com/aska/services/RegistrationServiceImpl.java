package com.aska.services;

import com.aska.forms.RegistrationForm;
import com.aska.models.user.User;
import com.aska.models.user.UserRole;
import com.aska.models.user.UserState;
import com.aska.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isExist (String email) throws SQLException {
        return userRepository.existsByEmail(email);
    }


    @Override
    public void registerUser(RegistrationForm registrationForm) throws SQLException {
            userRepository.save(new User.Builder()
                    .email(registrationForm.getUsername())
                    .country(registrationForm.getCountry())
                    .hashPassword(passwordEncoder.encode(registrationForm.getPassword()))
                    .birthday(registrationForm.getBirthday())
                    .role(UserRole.USER)
                    .state(UserState.ACTIVE)
                    .build());
    }
}
