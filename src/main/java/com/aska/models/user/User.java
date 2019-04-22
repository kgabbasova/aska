package com.aska.models.user;

import com.aska.models.survey.Survey;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;


@Entity
@Table(name = "survey_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String email;

    @Column(name = "hash_password")
    private String hashPassword;

    private String country;

    @Temporal(TemporalType.DATE)
    private Calendar birthday;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Enumerated(value = EnumType.STRING)
    private UserState state;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List <Survey> surveys;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", country='" + country + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                ", state=" + state +
                '}';
    }

    public User() {
    }

    public User(String email, String hashPassword, String country, Calendar birthday, UserRole role, UserState state) {
        this.email = email;
        this.hashPassword = hashPassword;
        this.country = country;
        this.birthday = birthday;
        this.role = role;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }


    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }


    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }


        public Builder id(Long id) {
            newUser.id = id;
            return this;
        }

        public Builder email(String email) {
            newUser.email = email;
            return this;
        }

        public Builder hashPassword(String hashPassword) {
            newUser.hashPassword = hashPassword;
            return this;
        }

        public Builder country(String country) {
            newUser.country = country;
            return this;
        }

        public Builder birthday(Calendar birthday) {
            newUser.birthday = birthday;
            return this;
        }

        public Builder role(UserRole role) {
            newUser.role = role;
            return this;
        }

        public Builder state(UserState state) {
            newUser.state = state;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
