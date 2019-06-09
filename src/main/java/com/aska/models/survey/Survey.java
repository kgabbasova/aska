package com.aska.models.survey;

import com.aska.models.user.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;


@Entity
@Table (name = "survey")
public class Survey {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "survey_id")
    private Long id;

    @Column(name = "survey_name")
    private String name;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User user;


    @OneToMany(mappedBy = "survey",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyQuestion> questions;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "show_mode")
    private ShowMode showMode;


    @Column (name = "results_show")
    private boolean resultsShow = true;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar created = new GregorianCalendar();


    @Column (name = "is_active")
    private boolean isActive;

    public Survey(String name, User user, List<SurveyQuestion> questions, ShowMode showMode, boolean resultsShow) {
        this.name = name;
        this.user = user;
        this.questions = questions;
        this.showMode = showMode;
        this.created = new GregorianCalendar();
        this.resultsShow = resultsShow;
        this.isActive = true;
    }

    public Survey () {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<SurveyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    public ShowMode getShowMode() {
        return showMode;
    }

    public void setShowMode(ShowMode showMode) {
        this.showMode = showMode;
    }

    public boolean isResultsShow() {
        return resultsShow;
    }

    public void setResultsShow(boolean resultsShow) {
        this.resultsShow = resultsShow;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", questions=" + questions +
                ", showMode=" + showMode +
                ", resultsShow=" + resultsShow +
                ", created=" + created +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Survey)) return false;
        Survey survey = (Survey) o;
        return
                Objects.equals(id, survey.id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.user);
        hash = 19 * hash + Objects.hashCode(this.showMode);
        hash = 19 * hash + Objects.hashCode(this.resultsShow);
        hash = 19 * hash + Objects.hashCode(this.created);
        hash = 19 * hash + Objects.hashCode(this.isActive);
        return hash;
    }
}
