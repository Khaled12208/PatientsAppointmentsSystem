package com.clinic.PatientsAppointmentsSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.clinic.PatientsAppointmentsSystem.repository")
@EntityScan("com.clinic.PatientsAppointmentsSystem.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class PatientsAppointmentsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsAppointmentsSystemApplication.class, args);
	}
}
