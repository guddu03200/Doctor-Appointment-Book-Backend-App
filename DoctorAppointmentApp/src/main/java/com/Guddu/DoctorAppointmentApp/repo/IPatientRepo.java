package com.Guddu.DoctorAppointmentApp.repo;

import com.Guddu.DoctorAppointmentApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepo extends JpaRepository<Patient, Long>{
    Patient findFirstByPatientEmail(String newEmail);
}
