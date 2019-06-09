package com.aska.exceptions;


public class InvalidSurveyCodeException extends RuntimeException {
    String message;

    public InvalidSurveyCodeException() {

    }

    public InvalidSurveyCodeException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
