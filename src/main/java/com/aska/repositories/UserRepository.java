package com.aska.repositories;

import com.aska.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String username);

    User findUserByEmail(String email);

    boolean existsByEmailAndSurveysId (String username, Long surveyId);

}
