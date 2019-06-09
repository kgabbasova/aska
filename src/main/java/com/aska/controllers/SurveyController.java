package com.aska.controllers;


import com.aska.forms.SurveyForm;
import com.aska.models.survey.ShowMode;
import com.aska.models.survey.Survey;
import com.aska.models.survey.SurveyQuestion;
import com.aska.services.QuestionService;
import com.aska.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(path = "/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private QuestionService questionService;


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createSurveyGet(Model model) throws SQLException {
        model.addAttribute("surveyForm", new SurveyForm());
        return "survey/surveyCreate";
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createSurveyPost(SurveyForm surveyForm, ModelMap modelMap, RedirectAttributes redirectAttributes) throws SQLException {
        surveyService.addSurveyFromForm(surveyForm);
        redirectAttributes.addFlashAttribute("message", "Your poll has been added");
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#profileGet").build();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteSurvey(@PathVariable(value = "id") Long id) {
        surveyService.deleteSurvey(id);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#profileGet").build();
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String surveyGet(@PathVariable(value = "id") Long id, ModelMap modelMap) {
        if (!surveyService.isSurveyExist(id)) {
            throw new NullPointerException();
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!surveyService.existUserSurvey(username, id)) {
            throw new AccessDeniedException(username);
        }
        Survey survey = surveyService.getSurveyByID(id);
        modelMap.addAttribute("survey", survey);
        return "survey/survey";
    }


    @RequestMapping(value = "/{id}/pass", method = RequestMethod.GET)
    public String passSurveyGet(@PathVariable(value = "id") Long id, ModelMap modelMap) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Survey survey = surveyService.getSurveyByID(id);
        modelMap.addAttribute("survey", survey);
        return "survey/surveyPass";
    }


    @RequestMapping(value = "/{id}/pass", method = RequestMethod.POST)
    public String passSurvey(ModelMap modelMap, @PathVariable(value = "id") Long id, HttpServletRequest request) {

        Map<String, String[]> answers = new HashMap<>(request.getParameterMap());
        answers.remove("_csrf");
        questionService.answersProcessing(id, answers);

        if (surveyService.getResultsShowById(id)) {
            SurveyQuestion question = questionService.getFirstQuestionBySurveyId(id);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("QC#questionResultsGet")
                    .arg(0, id).arg(1, question.getId()).build();
        } else
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#enterSurveyCode").build();
    }


    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public String passSurveyAccess(@RequestParam("surveyCode") String surveyCode, RedirectAttributes attributes) {


        try {
            String message = "Incorrect code";
            Long i = Long.parseLong(surveyCode);
            if (!surveyService.isSurveyExist(i)) {
                attributes.addFlashAttribute("message", message);
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#enterSurveyCode").build();
            }

            if (surveyService.getSurveyShowMode(i).equals(ShowMode.ONE)) {
                SurveyQuestion question = questionService.getFirstQuestionBySurveyId(i);
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("QC#passQuestionGet")
                        .arg(0, surveyCode).arg(1, question.getId()).build();

            }
        } catch (NumberFormatException ex) {
            String message = "Incorrect code";
            attributes.addFlashAttribute("message", message);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#enterSurveyCode").build();
        }
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#passSurveyGet").arg(0, surveyCode).build();
    }


    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public String enterSurveyCode() {
        return "survey/enterCode";
    }


    @RequestMapping(value = "/{id}/results", method = RequestMethod.GET)
    public String getSurveyResults(@PathVariable("id") Long sId) {
        Long qid = questionService.getFirstQuestionBySurveyId(sId).getId();
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("QC#questionResultsGet")
                .arg(0, sId).arg(1, qid).build();
    }
}
