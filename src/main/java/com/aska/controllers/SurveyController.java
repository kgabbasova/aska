package com.aska.controllers;


import com.aska.forms.SurveyForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/survey")
public class SurveyController {

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping (path = "/create", method = RequestMethod.GET)
    public String createSurveyGet (Model model) {
        model.addAttribute("surveyForm", new SurveyForm());
        return "poll_create";
    }

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createSurveyPost (SurveyForm surveyForm, ModelMap modelMap) {
        return "poll-create";
    }
}
