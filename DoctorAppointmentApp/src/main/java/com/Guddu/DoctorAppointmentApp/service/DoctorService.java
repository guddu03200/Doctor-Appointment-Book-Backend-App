package com.Guddu.DoctorAppointmentApp.service;

import com.Guddu.DoctorAppointmentApp.model.Doctor;
import com.Guddu.DoctorAppointmentApp.repo.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctorRepo doctorRepo;
    public void addDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
    }

    public List<Doctor> findAllDoctor() {
        return doctorRepo.findAll();
    }
}
