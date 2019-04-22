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

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    @Column(name = "is_right")
    private boolean right;

    @ManyToOne
    @JoinColumn (name = "question_id")
    private SurveyQuestion surveyQuestion;

    public SurveyQuestionAnswer(String description, boolean right, SurveyQuestion surveyQuestion) {
        this.description = description;
        this.right = right;
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




    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }

    @Override
    public String toString() {
        return "SurveyQuestionAnswer{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", right=" + right +
                ", surveyQuestion=" + surveyQuestion +
                '}';
    }
}
