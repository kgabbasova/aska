package com.aska.forms;

import com.aska.models.survey.SurveyQuestion;
import com.aska.models.survey.SurveyQuestionAnswer;

import java.util.List;

public class SurveyForm {
    private String name;

    private String showMode;

    private boolean resultsShow;

    private List <SurveyQuestion> questions;

    private List <SurveyQuestionAnswer> answers;

    public SurveyForm(String name, String showMode, boolean resultsShow, List<SurveyQuestion> questions, List<SurveyQuestionAnswer> answers) {
        this.name = name;
        this.showMode = showMode;
        this.resultsShow = resultsShow;
        this.questions = questions;
        this.answers = answers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowMode() {
        return showMode;
    }

    public void setShowMode(String showMode) {
        this.showMode = showMode;
    }

    public boolean isResultsShow() {
        return resultsShow;
    }

    public void setResultsShow(boolean resultsShow) {
        this.resultsShow = resultsShow;
    }



    public SurveyForm() {

    }

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    public List<SurveyQuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SurveyQuestionAnswer> answers) {
        this.answers = answers;
    }
}
