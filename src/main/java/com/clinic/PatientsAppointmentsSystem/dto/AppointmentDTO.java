package com.clinic.PatientsAppointmentsSystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AppointmentDTO {
    private Long appointmentId;
    private Long userId; // This should be updated if you're using a User object
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason; // Reason or service
    private String status;
}
