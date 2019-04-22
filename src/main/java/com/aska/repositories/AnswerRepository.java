package com.aska.repositories;

import com.aska.models.survey.SurveyQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<SurveyQuestionAnswer, Long> {

    @Override
    <S extends SurveyQuestionAnswer> S save(S s);
}
