package com.clinic.PatientsAppointmentsSystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactMsgDTO {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @NotBlank(message = "Phone cannot be empty")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot be longer than 100 characters")
    private String email;

    @NotBlank(message = "Subject cannot be empty")
    @Size(max = 100, message = "Subject cannot be longer than 100 characters")
    private String subject;

    @NotBlank(message = "Message cannot be empty")
    @Size(max = 500, message = "Message cannot be longer than 500 characters")
    private String message;

    @NotBlank(message = "Status cannot be empty")
    @Size(max = 10, message = "Status cannot be longer than 10 characters")
    private String status;

    @Size(max = 50, message = "UpdatedBy cannot be longer than 50 characters")
    private String updatedBy;
}
