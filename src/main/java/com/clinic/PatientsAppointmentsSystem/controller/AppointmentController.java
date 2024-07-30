package com.clinic.PatientsAppointmentsSystem.controller;

import com.clinic.PatientsAppointmentsSystem.dto.AppointmentDTO;
import com.clinic.PatientsAppointmentsSystem.dto.ContactMsgDTO;
import com.clinic.PatientsAppointmentsSystem.model.Appointment;
import com.clinic.PatientsAppointmentsSystem.model.ContactMsg;
import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.repository.UserRepository;
import com.clinic.PatientsAppointmentsSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/appointment")
    public String displayAppointmentPage(Model model) {
        model.addAttribute("appointment", new AppointmentDTO());
        return "appointment.html";
    }

    @RequestMapping(value = "/saveAppointment",method = POST)
    public String createUser(@ModelAttribute("appointment")AppointmentDTO appointmentDTO, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Appointment appointment =new Appointment();
        appointment.setUser(user);
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setReason(appointmentDTO.getReason());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setStatus("Pending");
        appointmentService.createAppointment(appointment);
        return "redirect:/dashboard";
    }
}