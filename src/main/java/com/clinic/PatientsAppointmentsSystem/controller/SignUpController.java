package com.clinic.PatientsAppointmentsSystem.controller;

import com.clinic.PatientsAppointmentsSystem.dto.UserDTO;
import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.model.UserRole;
import com.clinic.PatientsAppointmentsSystem.repository.UserRepository;
import com.clinic.PatientsAppointmentsSystem.repository.UserRoleRepository;
import com.clinic.PatientsAppointmentsSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class SignUpController {

    private UserService userService;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpController(UserService userService , PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository=userRoleRepository;
    }



    @RequestMapping("/signup")
    public String displaySignUpPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup.html";
    }

    @RequestMapping(value = "/saveUser",method = POST)
    public String createUser(@ModelAttribute("user")UserDTO userDTO, Model model) {
        UserRole userRole = userRoleRepository.findByRoleName("User")
                .orElseThrow(() -> new UsernameNotFoundException("Default role not found"));
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userRole);
        userService.createUser(user);
        return "redirect:/login";
    }

}