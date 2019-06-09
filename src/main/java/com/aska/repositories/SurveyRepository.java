package com.aska.repositories;


import com.aska.models.survey.ShowMode;
import com.aska.models.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {


    @Override
    Survey save(Survey survey);


    List<Survey> findAllByUser_Email(String email);


    @Override
    Optional<Survey> findById(Long aLong);

    @Override
    void deleteById(Long aLong);


    Survey getById(Long id);

    @Query("select resultsShow from Survey s where s.id=:id")
    boolean getResultsShowById (@Param("id") Long id);



    @Query ("select showMode from Survey s where s.id=:id")
    ShowMode getShowModeById(@Param("id")Long id);

}
