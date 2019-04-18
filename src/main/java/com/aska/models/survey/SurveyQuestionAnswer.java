package com.aska.models.survey;

import javax.persistence.*;

@Entity
@Table (name = "survey_answer")
public class SurveyQuestionAnswer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "answer_id")
    private Long id;

    private String description;

    @Column(name = "is_right")
    private boolean isRight;

    @ManyToOne
    @JoinColumn (name = "question_id")
    private SurveyQuestion surveyQuestion;

    public SurveyQuestionAnswer(String description, boolean isRight, SurveyQuestion surveyQuestion) {
        this.description = description;
        this.isRight = isRight;
        this.surveyQuestion = surveyQuestion;
    }

    public  SurveyQuestionAnswer () {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }


    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }
}
