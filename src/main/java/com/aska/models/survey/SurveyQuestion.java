package com.aska.models.survey;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "survey_question")
public class SurveyQuestion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "question_id")
    private Long id;


    private String description;


    private String type;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "surveyQuestion")
    private List<SurveyQuestionAnswer> questionAnswers;


    @ManyToOne
    @JoinColumn (name = "survey_id")
    private Survey survey;


    public  SurveyQuestion () {


    }
    public SurveyQuestion(String description, String type, List<SurveyQuestionAnswer> questionAnswers) {
        this.description = description;
        this.type = type;
        this.questionAnswers = questionAnswers;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<SurveyQuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }


    public void setQuestionAnswers(List<SurveyQuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }


    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
