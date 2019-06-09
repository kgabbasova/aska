package com.aska.services;

import com.aska.models.survey.AnswerData;
import com.aska.models.survey.QuestionResults;
import com.aska.models.survey.SurveyQuestion;
import com.aska.models.survey.SurveyQuestionAnswer;
import com.aska.repositories.AnswerRepository;
import com.aska.repositories.QuestionRepository;
import com.aska.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private SurveyRepository surveyRepository;


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;


    @Override
    public void answersProcessing(Long surveyId, Map<String, String[]> answers) {
        for (Map.Entry<String, String[]> entry : answers.entrySet()) {
            String[] a = entry.getValue();
            for (int i = 0; i < a.length; i++) {
                answerRepository.incrementVotes(Long.valueOf(a[i]));
            }
            questionRepository.incrementVotes(Long.valueOf(entry.getKey()));
        }
    }

    @Override
    public SurveyQuestion getQuestion(Long surveyId, Long questionId) {
        SurveyQuestion question = questionRepository.getById(questionId);
        question.setSurvey(surveyRepository.getById(surveyId));
        question.setQuestionAnswers(answerRepository.getBySurveyQuestion_Id(questionId));
        return question;
    }

    @Override
    public QuestionResults getQuestionResultsUpdate(Long questionId) {
        QuestionResults results = new QuestionResults();


        List<SurveyQuestionAnswer> answers = answerRepository.getBySurveyQuestion_Id(questionId);
        AnswerData[] values = new AnswerData[answers.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = new AnswerData();
            values[i].setLabel(answers.get(i).getDescription());
            values[i].setValue(answers.get(i).getVotes());
        }
        results.setValues(values);
        results.setVotes(questionRepository.getVotes(questionId));
        return results;
    }


    @Override
    public boolean isQuestionExist(Long id) {
        return questionRepository.existsById(id);
    }

    @Override
    public boolean isAnswerExist(Long id) {
        return answerRepository.existsById(id);
    }

    @Override
    public List<SurveyQuestion> getQuestionsBySurveyId(Long id) {
        return questionRepository.getBySurvey_Id(id);
    }

    @Override
    public SurveyQuestion getFirstQuestionBySurveyId(Long id) {
        return questionRepository.getFirstBySurvey_Id(id);
    }

    @Override
    public SurveyQuestion getNextQuestion(Long sId, Long qId) {
        return questionRepository.getFirstBySurvey_IdAndIdGreaterThan(sId, qId);
    }

    @Override
    public SurveyQuestion getPrevQuestion(Long sId, Long qId) {
        return questionRepository.getFirstBySurvey_IdAndIdLessThan(sId, qId);
    }

    @Override
    public List<SurveyQuestionAnswer> getQuestionAnswers (Long qId) {
        return answerRepository.getBySurveyQuestion_Id(qId);
    }
}



