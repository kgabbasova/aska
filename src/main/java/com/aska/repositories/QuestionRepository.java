package com.aska.repositories;

import com.aska.models.survey.SurveyQuestion;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface QuestionRepository extends JpaRepository<SurveyQuestion, Long> {

    @Override
    <S extends SurveyQuestion> S save(S s);


    <S extends SurveyQuestion> List<S> getBySurvey_Id(Long id);


    SurveyQuestion getById(Long id);


    @Modifying
    @Transactional
    @Query("update SurveyQuestion q set q.votes = q.votes+1 where q.id = :id")
    void incrementVotes(@Param("id") Long id);


    @Query("select votes from SurveyQuestion q where q.id=:id")
    Integer getVotes(@Param("id") Long id);


    SurveyQuestion getFirstBySurvey_Id(Long surveyId);

    SurveyQuestion getFirstBySurvey_IdAndIdGreaterThan(Long sId, Long qId);

    SurveyQuestion getFirstBySurvey_IdAndIdLessThan (Long sid, Long qId);
}
