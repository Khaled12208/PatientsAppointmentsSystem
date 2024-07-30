package com.clinic.PatientsAppointmentsSystem.service;

import com.clinic.PatientsAppointmentsSystem.model.ContactMsg;
import com.clinic.PatientsAppointmentsSystem.model.User;
import com.clinic.PatientsAppointmentsSystem.repository.ContactMsgRepository;
import com.clinic.PatientsAppointmentsSystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactMsgRepository contactMsgRepository;

    public ContactMsg createMsg(ContactMsg message) {
        return contactMsgRepository.save(message);
    }

    public List<ContactMsg> getAllMsg() {
        return contactMsgRepository.findAll();
    }

    public Optional<ContactMsg> getMessageById(Integer id) {
        return contactMsgRepository.findByContactId(id);
    }
}
