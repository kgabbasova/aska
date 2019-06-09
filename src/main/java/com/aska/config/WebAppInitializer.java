package com.aska.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigApplicationContext rootConfig = new AnnotationConfigApplicationContext();
        rootConfig.register(new Class[]{DBConfig.class, SecurityConfig.class});

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebConfig.class);



        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        ServletRegistration.Dynamic registration = servletContext.addServlet("WebConfig", dispatcherServlet);
        registration.setLoadOnStartup(1);

        registration.addMapping("/");

    }
}
