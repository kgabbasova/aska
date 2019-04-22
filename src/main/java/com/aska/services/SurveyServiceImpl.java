package com.aska.services;

import com.aska.forms.SurveyForm;
import com.aska.models.survey.ShowMode;
import com.aska.models.survey.Survey;
import com.aska.models.survey.SurveyQuestion;
import com.aska.models.survey.SurveyQuestionAnswer;
import com.aska.models.user.User;
import com.aska.repositories.AnswerRepository;
import com.aska.repositories.QuestionRepository;
import com.aska.repositories.SurveyRepository;
import com.aska.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;


    public List<Survey> getUserSurveys(String name) throws SQLException {
        User user = userRepository.findUserByEmail(name);
        return surveyRepository.findAllByUser(user);
    }

    public void addSurveyFromForm(SurveyForm surveyForm) throws SQLException {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Survey survey = new Survey(surveyForm.getName(), user, surveyForm.getQuestions(), ShowMode.valueOf(surveyForm.getShowMode()), surveyForm.isResultsShow());
        for (SurveyQuestion q : survey.getQuestions()) {
            q.setSurvey(survey);
            for (SurveyQuestionAnswer a : q.getQuestionAnswers()) {
                a.setSurveyQuestion(q);
            }
        }
        addSurvey(survey);
    }


    @Override
    public void addSurvey(Survey survey) {
        surveyRepository.save(survey);
    }
}
