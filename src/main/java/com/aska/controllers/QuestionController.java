package com.aska.controllers;


import com.aska.models.survey.QuestionResults;
import com.aska.models.survey.SurveyQuestion;
import com.aska.services.QuestionService;
import com.aska.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class QuestionController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = "/survey/{surveyId}/question/{questionId}/results", method = RequestMethod.GET)
    public String questionResultsGet(@PathVariable("surveyId") Long sId, @PathVariable("questionId") Long qId, ModelMap modelMap) {
        if (!surveyService.isSurveyExist(sId) || !questionService.isQuestionExist(qId)
                || !surveyService.existSurveyQuest(sId, qId)) {
            throw new NullPointerException();
        }
        if (!surveyService.getResultsShowById(sId)) {
            if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
                throw new AccessDeniedException("Anonymous");
            }
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if (!surveyService.existUserSurvey(username, sId)) {
                throw new AccessDeniedException(username);
            }
        }

        SurveyQuestion questionResults = questionService.getQuestion(sId, qId);
        SurveyQuestion nextQ = questionService.getNextQuestion(sId, qId);
        SurveyQuestion prevQ = questionService.getPrevQuestion(sId, qId);

        modelMap.addAttribute("nextQuestion", nextQ);
        modelMap.addAttribute("previousQuestion", prevQ);
        modelMap.addAttribute("question", questionResults);

        return "survey/questionResults";
    }

    @RequestMapping(value = "/survey/{surveyId}/question/{questionId}/results-data", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    QuestionResults questionResults(@PathVariable("surveyId") Long sId, @PathVariable("questionId") Long qId) {

        return questionService.getQuestionResultsUpdate(qId);
    }

    @RequestMapping(value = "/survey/{surveyId}/question/{questionId}/pass", method = RequestMethod.GET)
    public String passQuestionGet(@PathVariable("surveyId") Long sId, @PathVariable("questionId") Long qId, ModelMap modelMap) {

        SurveyQuestion question = questionService.getQuestion(sId, qId);
        modelMap.addAttribute("question", question);

        return "survey/questionPass";
    }

    @RequestMapping(value = "/survey/{surveyId}/question/{questionId}/pass", method = RequestMethod.POST)
    public String passQuestion(@PathVariable("surveyId") Long sId, @PathVariable("questionId") Long qId, HttpServletRequest request) {
        Map<String, String[]> answers = new HashMap<>(request.getParameterMap());
        answers.remove("_csrf");
        questionService.answersProcessing(sId, answers);
        SurveyQuestion nextQ = questionService.getNextQuestion(sId, qId);
        if (nextQ != null) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("QC#passQuestionGet")
                    .arg(0, sId).arg(1, nextQ.getId()).build();
        }


        if (surveyService.getResultsShowById(sId)) {
            SurveyQuestion question = questionService.getFirstQuestionBySurveyId(sId);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("QC#questionResultsGet")
                    .arg(0, sId).arg(1, question.getId()).build();
        }
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#enterSurveyCode").build();
    }

}
