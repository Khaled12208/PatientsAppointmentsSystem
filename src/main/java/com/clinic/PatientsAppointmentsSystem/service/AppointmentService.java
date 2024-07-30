package com.clinic.PatientsAppointmentsSystem.service;

import com.clinic.PatientsAppointmentsSystem.model.Appointment;
import com.clinic.PatientsAppointmentsSystem.model.ContactMsg;
import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.repository.AppointmentRepository;
import com.clinic.PatientsAppointmentsSystem.repository.ContactMsgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsByUser(User user) {
        if("ADMIN".equalsIgnoreCase(user.getRole().getRoleName()))
        {
            return appointmentRepository.findAll();
        }else
        {
            return appointmentRepository.findByUser(user);

        }
    }
}
