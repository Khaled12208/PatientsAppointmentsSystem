package com.clinic.PatientsAppointmentsSystem.service;

import com.clinic.PatientsAppointmentsSystem.dto.UserDTO;
import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.model.UserRole;
import com.clinic.PatientsAppointmentsSystem.repository.UserRepository;
import com.clinic.PatientsAppointmentsSystem.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;
    @Autowired

    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDTO userDTO) {
        UserRole userRole = userRoleRepository.findByRoleName(userDTO.getRole())
                .orElseThrow(() -> new UsernameNotFoundException("Default role not found"));
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userRole);
        return userRepository.save(user);
    }

    public boolean userExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
