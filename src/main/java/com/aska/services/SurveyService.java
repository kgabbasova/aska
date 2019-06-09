package com.aska.services;

import com.aska.forms.SurveyForm;
import com.aska.models.survey.*;

import java.sql.SQLException;
import java.util.List;



public interface SurveyService {

    void addSurvey(Survey survey);

    List<Survey> getUserSurveys(String name);

    void addSurveyFromForm(SurveyForm surveyForm);

    void deleteSurvey(Long id);

    Survey getSurveyByID(Long id);

    boolean isSurveyExist(Long id);

    Survey getUserSurvey(String username, Long surveyId);

    boolean getResultsShowById(Long id);

    ShowMode getSurveyShowMode(Long sId);

}


