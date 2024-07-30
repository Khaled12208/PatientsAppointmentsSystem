package com.clinic.PatientsAppointmentsSystem.service;

import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }


}
