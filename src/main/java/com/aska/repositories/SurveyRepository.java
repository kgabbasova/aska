package com.aska.repositories;


import com.aska.models.survey.Survey;
import com.aska.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Survey findAllByUser (User user);


    @Override
     Survey save(Survey survey);
}
