package com.aska.services;

import com.aska.models.survey.ShowMode;
import com.aska.models.survey.Survey;
import com.aska.models.user.User;
import com.aska.repositories.SurveyRepository;
import com.aska.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

    public void testing() throws SQLException {
        User user = userRepository.findUserByEmail("kami@gm.com");
        Survey survey = new Survey("jasper", user, null, ShowMode.ALL, true);
        addSurvey(survey);
    }

    public List<Survey> getUserSurveys (String name) throws SQLException {
        User user = userRepository.findUserByEmail(name);
        return user.getSurveys();
    }

    @Override
    public void addSurvey(Survey survey) {

        User owner = survey.getUser();
        owner.getSurveys().add(survey);
        surveyRepository.save(survey);
    }
}
