package com.clinic.PatientsAppointmentsSystem.controller;

import com.clinic.PatientsAppointmentsSystem.dto.UserDTO;
import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.model.UserRole;
import com.clinic.PatientsAppointmentsSystem.repository.UserRoleRepository;
import com.clinic.PatientsAppointmentsSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class SignUpController {

    private UserService userService;


    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;

    }


    @RequestMapping(value = "/signup",method = {RequestMethod.GET, RequestMethod.POST})
    public String displaySignUpPage(@RequestParam(value = "success", required = false) String success, Model model, Authentication authentication) {
        model.addAttribute("user", new UserDTO());
        boolean isDisabled = !hasRole("ROLE_Admin",authentication);
        model.addAttribute("isDisabled", isDisabled);
        String message = null;
        if ("true".equalsIgnoreCase(success)) {
            message = "user has been created successfully";
        }
        model.addAttribute("successMessage", message);
        return "signup.html";
    }

    @RequestMapping(value = "/saveUser",method = POST)
    public String createUser(@Valid @ModelAttribute("user")UserDTO userDTO, Errors errors,  RedirectAttributes redirectAttributes,Authentication authentication) {
        if (errors.hasErrors()) {
            return "redirect:/signup";
        }
        // Check if passwords match
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "error.user", "Passwords do not match");
            return "signup";
        }
        // Check if username already exists
        if (userService.userExistsByUsername(userDTO.getUsername())) {
            errors.rejectValue("username", "error.user", "Username is already taken");
            return "signup";
        }
        // Check if email already exists
        if (userService.userExistsByEmail(userDTO.getEmail())) {
            errors.rejectValue("email", "error.user", "Email is already taken");
            return "signup";
        }

        if (userDTO.getRole().equals("Admin") && !hasRole("ROLE_Admin",authentication)) {
            errors.rejectValue("role", "error.user", "Cant not set user to admin");
            return "signup";
        }

        User user = userService.createUser(userDTO);

        boolean success = user != null;
        redirectAttributes.addAttribute("success", success ? "true" : "false");

        return "redirect:/signup";
    }

    private boolean hasRole(String role, Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role))) {
            return true;
        }
        return false;

    }
}