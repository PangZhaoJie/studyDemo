package com.baobaotao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by pangzhaojie on 17-8-8.
 */
public class WebInitiallizer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext configApplicationContext = new AnnotationConfigWebApplicationContext();
        configApplicationContext.register(BeanConfig.class);
        configApplicationContext.setServletContext(servletContext);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcher", new DispatcherServlet(configApplicationContext));
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);
    }
}
