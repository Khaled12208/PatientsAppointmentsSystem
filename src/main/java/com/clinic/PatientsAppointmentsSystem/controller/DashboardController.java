package com.clinic.PatientsAppointmentsSystem.controller;

import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.repository.UserRepository;
import com.clinic.PatientsAppointmentsSystem.service.AppointmentService;
import com.clinic.PatientsAppointmentsSystem.service.UserService;
import com.clinic.PatientsAppointmentsSystem.untils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping("/dashboard")
    public String displayHomePage(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        model.addAttribute("appointments", appointmentService.getAllAppointmentsByUser(user));
        model.addAttribute("username", StringUtils.toCamelCase(user.getFirstName())+" "+StringUtils.toCamelCase(user.getLastName()));
        return "dashboard.html";
    }

}