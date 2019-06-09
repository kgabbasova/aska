package com.aska.repositories;

import com.aska.models.survey.SurveyQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<SurveyQuestionAnswer, Long> {

    @Override
    <S extends SurveyQuestionAnswer> S save(S s);

    <S extends SurveyQuestionAnswer>List<S> getBySurveyQuestion_Id(Long id);


    Optional <List<SurveyQuestionAnswer>> findAllBySurveyQuestion_Id(Iterable<Long> iterable);

    @Modifying
    @Transactional
    @Query("update SurveyQuestionAnswer a set a.votes = a.votes+1 where a.id = :id")
    void incrementVotes(@Param("id") Long id);



}
