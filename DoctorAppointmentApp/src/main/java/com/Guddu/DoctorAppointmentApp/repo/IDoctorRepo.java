package com.Guddu.DoctorAppointmentApp.repo;

import com.Guddu.DoctorAppointmentApp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
}
