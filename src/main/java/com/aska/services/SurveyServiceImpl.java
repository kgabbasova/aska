package com.aska.services;

import com.aska.forms.SurveyForm;
import com.aska.models.survey.*;
import com.aska.models.user.User;
import com.aska.repositories.SurveyRepository;
import com.aska.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private QuestionService questionService;


    public List<Survey> getUserSurveys(String name) {
        return surveyRepository.findAllByUser_Email(name);
    }

    public void addSurveyFromForm(SurveyForm surveyForm) {
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

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public boolean isSurveyExist(Long id) {
        return surveyRepository.existsById(id);
    }

    @Override
    public Survey getSurveyByID(Long id) {

        Survey survey = surveyRepository.getById(id);
        List<SurveyQuestion> questions = questionService.getQuestionsBySurveyId(id);
        for (SurveyQuestion question : questions) {
            question.setQuestionAnswers(questionService.getQuestionAnswers(question.getId()));
        }
        survey.setQuestions(questions);
        return survey;
    }


    @Override
    public boolean existUserSurvey(String username, Long surveyId) {

        return userRepository.existsByEmailAndSurveysId(username, surveyId);
    }

    @Override
    public boolean getResultsShowById(Long id) {
        return surveyRepository.getResultsShowById(id);
    }


    @Override
    public ShowMode getSurveyShowMode(Long sId) {
        return surveyRepository.getShowModeById(sId);
    }

    @Override
    public boolean existSurveyQuest(Long survId, Long questId) {
        return surveyRepository.existsByIdAndQuestionsId(survId, questId);
    }

}
