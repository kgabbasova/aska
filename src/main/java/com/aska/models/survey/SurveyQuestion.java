package com.aska.models.survey;

import java.util.List;

public class SurveyQuestion {

    private Long id;

    private String description;

    private String type;

    private List<SurveyQuestionAnswer> questionAnswers;

    private Survey survey;

}
