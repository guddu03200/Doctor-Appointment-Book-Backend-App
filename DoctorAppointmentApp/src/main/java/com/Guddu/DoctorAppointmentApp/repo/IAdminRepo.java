package com.Guddu.DoctorAppointmentApp.repo;

import com.Guddu.DoctorAppointmentApp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin, Long> {
}
