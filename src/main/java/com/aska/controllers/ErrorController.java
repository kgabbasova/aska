package com.aska.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ErrorController {

    private final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    private static final String DEFAULT_ERROR_VIEW = "errors/error";


    @ResponseStatus(HttpStatus.NOT_FOUND)

    @ExceptionHandler(value = {NullPointerException.class, NoHandlerFoundException.class})
    public ModelAndView NotFoundErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("[URL] : {}", request.getRequestURL(), e);

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "Page not found");
        mav.addObject(request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView AccessDeniedHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("[URL] : {}", request.getRequestURL(), e);

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "Access denied");
        mav.addObject(request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("[URL] : {}", req.getRequestURL(), e);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Oops, something went wrong...");
        mav.addObject(req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}


