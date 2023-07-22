package com.Guddu.DoctorAppointmentApp.repo;

import com.Guddu.DoctorAppointmentApp.model.AuthenticationToken;
import com.Guddu.DoctorAppointmentApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findFirstByTokenValue(String authToken);

    AuthenticationToken findFirstByPatient(Patient patient);
}
