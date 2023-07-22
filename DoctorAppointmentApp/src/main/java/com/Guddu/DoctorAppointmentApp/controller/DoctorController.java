package com.Guddu.DoctorAppointmentApp.controller;

import com.Guddu.DoctorAppointmentApp.model.Doctor;
import com.Guddu.DoctorAppointmentApp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("doctor")
    public void addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
    }

    @GetMapping("doctors")
    public List<Doctor> findAllDoctor(){
        return doctorService.findAllDoctor();
    }
}
