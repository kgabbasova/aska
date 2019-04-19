package com.aska.forms;

import com.aska.models.survey.SurveyQuestion;
import com.aska.models.survey.SurveyQuestionAnswer;

import java.util.List;
import java.util.Objects;

public class SurveyForm {
    private String name;

    private String showMode;

    private boolean resultsShow;

    private SurveyQuestion question;

    private SurveyQuestionAnswer answer;


    public SurveyForm() {

    }

    public SurveyForm(String name, String showMode, boolean resultsShow,
                       SurveyQuestion question, SurveyQuestionAnswer answer) {
        this.name = name;
        this.showMode = showMode;
        this.resultsShow = resultsShow;
        this.question = question;
        this.answer = answer;
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


    public SurveyQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SurveyQuestion question) {
        this.question = question;
    }

    public SurveyQuestionAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(SurveyQuestionAnswer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SurveyForm{" +
                "name='" + name + '\'' +
                ", showMode='" + showMode + '\'' +
                ", resultsShow=" + resultsShow +
                ", question=" + question +
                ", answer=" + answer +
                '}';
    }
}
