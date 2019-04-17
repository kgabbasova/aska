package com.aska.controllers;


import com.aska.forms.RegistrationForm;
import com.aska.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.text.ParseException;

@Controller
public class UserController {


    @Autowired
    private RegistrationService registrationService;


    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String registrationGet(Model model) throws ParseException {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }


    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String registrationPost(@Valid RegistrationForm registrationForm,
                                   BindingResult bindingResult,
                                   ModelMap modelMap) throws SQLException {
        if (registrationService.isExist(registrationForm.getUsername())) {
            modelMap.addAttribute("message", "User with the same username already exists!");
            return "registration";
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            registrationService.registerUser(registrationForm);
            modelMap.addAttribute("message", "User " + registrationForm.getUsername() + " has been successfully registered!");
            return "registration";
        }
    }


    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginGet(ModelMap modelMap) {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(ModelMap modelMap, HttpServletRequest request) {
        if (request.getParameterMap().containsKey("error")) {
            modelMap.addAttribute("error", true);
        }
        return "login";
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String homeGet(ModelMap modelMap) {
        return "home";
    }

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String profileGet (ModelMap modelMap) {
        return "profile";
    }



}
