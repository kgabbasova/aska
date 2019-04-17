package com.aska.models.survey;

import java.util.Calendar;
import java.util.List;

public class Survey {

    private Long id;


    private String name;

    private List<SurveyQuestion> questions;

    //    private String showMode;
    private enum showMode {
        ALL,
        TIME,
        AUTHOR,
        RESPONDENT;
    }

    private boolean resultsShow;

    private Calendar created;

    private boolean isActive;
}
