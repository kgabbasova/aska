package com.aska.forms;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Calendar;


@FieldMatch(baseField = "password", matchField = "passwordRepeat", message = "Passwords do not match!" )
public class RegistrationForm {

    @Email(regexp = "^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:" +
            "[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*" +
            "(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$",
            message = "Invalid email!")
    private String username;


    @Size(min = 8, max = 24, message = "Password should be at least 8 characters and at most 24 characters!")
    @Pattern(regexp = "(^[A-z]+[0-9]+[A-z0-9]*$)|(^[0-9]+[A-z]+[A-z0-9]*$)", message = "Password should contain digits and letters!")
    private String password;

    @NotNull
    private String passwordRepeat;

    @NotNull
    private String country;


    @PastOrPresent(message = "Sorry, it seems you are not born yet!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar birthday;


    public RegistrationForm(String username, String password, String passwordRepeat, String country, Calendar birthday) {
        this.username = username;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
        this.country = country;
        this.birthday = birthday;
    }

    public RegistrationForm() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
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
}
