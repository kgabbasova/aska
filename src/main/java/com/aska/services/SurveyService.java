package com.aska.services;

import com.aska.forms.SurveyForm;
import com.aska.models.survey.Survey;

import java.sql.SQLException;
import java.util.List;

public interface SurveyService {

    void addSurvey (Survey survey);

    List<Survey> getUserSurveys (String name) throws SQLException;

    public void addSurveyFromForm (SurveyForm surveyForm) throws SQLException;

    public void deleteSurvey (Long id );
}
