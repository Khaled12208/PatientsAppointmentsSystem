package com.clinic.PatientsAppointmentsSystem.repository;

import com.clinic.PatientsAppointmentsSystem.model.Appointment;
import com.clinic.PatientsAppointmentsSystem.model.ContactMsg;
import com.clinic.PatientsAppointmentsSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactMsgRepository extends JpaRepository<ContactMsg, Integer> {

    Optional<ContactMsg> findByContactId(Integer contactId);

}
