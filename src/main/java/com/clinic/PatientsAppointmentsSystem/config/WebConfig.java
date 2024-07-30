package com.clinic.PatientsAppointmentsSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/appointment").setViewName("appointment");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/message").setViewName("message");
        registry.addViewController("/message-details").setViewName("message-details");

    }

}
