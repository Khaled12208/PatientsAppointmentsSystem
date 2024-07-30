package com.clinic.PatientsAppointmentsSystem.controller;

import com.clinic.PatientsAppointmentsSystem.dto.ContactMsgDTO;
import com.clinic.PatientsAppointmentsSystem.dto.UserDTO;
import com.clinic.PatientsAppointmentsSystem.model.ContactMsg;
import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.model.UserRole;
import com.clinic.PatientsAppointmentsSystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("message", new ContactMsgDTO());
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg",method = POST)
    public String createUser(@ModelAttribute("user")ContactMsgDTO messageDto, Model model) {
        ContactMsg message =new ContactMsg();
        message.setSubject(messageDto.getSubject());
        message.setMessage(messageDto.getMessage());
        message.setEmail(messageDto.getEmail());
        message.setPhone(messageDto.getPhone());
        message.setName(messageDto.getName());
        message.setStatus("OPEN");
        contactService.createMsg(message);
        return "redirect:/contact";
    }

}