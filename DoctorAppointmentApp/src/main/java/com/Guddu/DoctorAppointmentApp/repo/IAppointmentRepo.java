package com.Guddu.DoctorAppointmentApp.repo;

import com.Guddu.DoctorAppointmentApp.model.Appointment;
import com.Guddu.DoctorAppointmentApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    Appointment  findFirstByPatient(Patient patient);
}
