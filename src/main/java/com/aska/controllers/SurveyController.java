package com.aska.controllers;


import com.aska.forms.SurveyForm;
import com.aska.models.user.User;
import com.aska.repositories.SurveyRepository;
import com.aska.services.SurveyService;
import com.aska.services.SurveyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping(path = "/survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;


    @PreAuthorize("isAuthenticated()")
    @RequestMapping (path = "/create", method = RequestMethod.GET)
    public String createSurveyGet (Model model) throws SQLException {
//        surveyService.testing();
        model.addAttribute("surveyForm", new SurveyForm());
        return "survey/survey-create";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createSurveyPost (SurveyForm surveyForm, ModelMap modelMap, RedirectAttributes redirectAttributes) throws SQLException {
        System.out.println(surveyForm);
        surveyService.addSurveyFromForm(surveyForm);
        redirectAttributes.addFlashAttribute("message", "Your poll has been added");
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#profileGet").build();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping (value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteSurvey (@PathVariable(value = "id") Long id) {
        surveyService.deleteSurvey(id);
        return "redirect:"+MvcUriComponentsBuilder.fromMappingName("UC#profileGet").build();
    }
}
