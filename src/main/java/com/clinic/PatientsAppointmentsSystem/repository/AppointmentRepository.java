package com.clinic.PatientsAppointmentsSystem.repository;

import com.clinic.PatientsAppointmentsSystem.model.Appointment;
import com.clinic.PatientsAppointmentsSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUser(User user);
}