package com.Guddu.DoctorAppointmentApp.service;

import com.Guddu.DoctorAppointmentApp.model.Appointment;
import com.Guddu.DoctorAppointmentApp.model.Patient;
import com.Guddu.DoctorAppointmentApp.repo.IAppointmentRepo;
import com.Guddu.DoctorAppointmentApp.repo.IDoctorRepo;
import com.Guddu.DoctorAppointmentApp.repo.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    IAppointmentRepo appointmentRepo;




    public List<Appointment> getAllAppointment() {
        return appointmentRepo.findAll();
    }

    public void save(Appointment appointment) {
        appointment.setAppointmentCreationTime(LocalDateTime.now());
        appointmentRepo.save(appointment);
    }

    public Appointment getAllAppointmentForPatient(Patient patient) {
        return appointmentRepo.findFirstByPatient(patient);
    }

    public void cancelAppointment(Appointment appointment) {
        appointmentRepo.delete(appointment);
    }
}
