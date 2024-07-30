package com.clinic.PatientsAppointmentsSystem.controller;

import com.clinic.PatientsAppointmentsSystem.model.ContactMsg;
import com.clinic.PatientsAppointmentsSystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private ContactService contactService;
    @GetMapping
    public String displayMessages(Model model) {
        List<ContactMsg> msg = contactService.getAllMsg();
        model.addAttribute("messages", msg);
        return "message.html";
    }

    @GetMapping("/{id}")
    public String messageDetails(@PathVariable("id") Integer id, Model model) {
        Optional<ContactMsg> message = contactService.getMessageById(id);
        if (message.isPresent()) {
            model.addAttribute("message", message.get());
            return "message-details";
        } else {
            return "redirect:/message";
        }
    }

}