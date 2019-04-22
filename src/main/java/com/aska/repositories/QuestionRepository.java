package com.aska.repositories;

import com.aska.models.survey.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository <SurveyQuestion, Long> {

    @Override
    <S extends SurveyQuestion> S save(S s);
}
