package com.aska.controllers;


import com.aska.forms.SurveyForm;
import com.aska.services.SurveyService;
import com.aska.services.SurveyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping(path = "/survey")
public class SurveyController {

    @Autowired
    SurveyServiceImpl surveyService;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping (path = "/create", method = RequestMethod.GET)
    public String createSurveyGet (Model model) throws SQLException {
        surveyService.testing();
        model.addAttribute("surveyForm", new SurveyForm());
        return "survey/survey-create";
    }

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createSurveyPost (SurveyForm surveyForm, ModelMap modelMap) {
        System.out.println(surveyForm);
        return "survey/survey-create";
    }
}
