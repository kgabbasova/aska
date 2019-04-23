package com.aska.repositories;


import com.aska.models.survey.Survey;
import com.aska.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {



    @Override
     Survey save(Survey survey);


    List<Survey> findAllByUser(User user);

    @Override
    void deleteById(Long aLong);

    Survey getById (Long id);
}
