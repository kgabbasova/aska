package com.aska.models.survey;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "survey_answer")
public class SurveyQuestionAnswer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "answer_id")
    private Long id;

    private String description;



    @Column(name = "is_right")
    private boolean right;


    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Column
    private Integer votes = 0;

    @ManyToOne (fetch = FetchType.LAZY)
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

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
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
                ", votes=" + votes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SurveyQuestionAnswer)) return false;
        SurveyQuestionAnswer that = (SurveyQuestionAnswer) o;
        return Objects.equals(id, that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
