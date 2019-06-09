package com.aska.models.survey;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "survey_question")
public class SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;


    private String description;


    private String type;

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    private Integer votes = 0;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "surveyQuestion")
    private List<SurveyQuestionAnswer> questionAnswers;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;


    public SurveyQuestion() {


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


    @Override
    public String toString() {
        return "SurveyQuestion{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", votes=" + votes +
                ", questionAnswers=" + questionAnswers +
                ", survey=" + survey +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SurveyQuestion)) return false;
        SurveyQuestion question = (SurveyQuestion) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.type);
        hash = 19 * hash + Objects.hashCode(this.votes);
        hash = 19 * hash + Objects.hashCode(this.survey);

        return hash;
    }
}
