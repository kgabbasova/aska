package com.aska.services;

import com.aska.models.survey.QuestionResults;
import com.aska.models.survey.SurveyQuestion;
import com.aska.models.survey.SurveyQuestionAnswer;

import java.util.List;
import java.util.Map;

public interface QuestionService {



    void answersProcessing(Long surveyId, Map<String, String[]> answers);

    SurveyQuestion getQuestion(Long surveyId, Long questionId);

    QuestionResults getQuestionResultsUpdate(Long questionId);

    boolean isQuestionExist(Long id);

    boolean isAnswerExist(Long id);

    List<SurveyQuestion> getQuestionsBySurveyId(Long id);

    SurveyQuestion getFirstQuestionBySurveyId(Long id);

    SurveyQuestion getNextQuestion(Long sId, Long qId);

    SurveyQuestion getPrevQuestion(Long sId, Long qId);

    List<SurveyQuestionAnswer> getQuestionAnswers (Long qId);

}
