package com.aska.forms;

import com.aska.models.survey.SurveyQuestion;
import com.aska.models.survey.SurveyQuestionAnswer;

import java.util.List;
import java.util.Objects;


public class SurveyForm {
    private String name;

    private String showMode;

    private boolean resultsShow;



    private List<SurveyQuestion> questions;


    public SurveyForm() {

    }

    public SurveyForm(String name, String showMode, boolean resultsShow, List<SurveyQuestion> questions) {
        this.name = name;
        this.showMode = showMode;
        this.resultsShow = resultsShow;
        this.questions = questions;
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

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "SurveyForm{" +
                "name='" + name + '\'' +
                ", showMode='" + showMode + '\'' +
                ", resultsShow=" + resultsShow +
                ", questions=" + questions +
                '}';
    }
}
