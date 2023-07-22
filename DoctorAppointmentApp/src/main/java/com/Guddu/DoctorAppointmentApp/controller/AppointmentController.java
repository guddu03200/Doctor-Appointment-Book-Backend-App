package com.Guddu.DoctorAppointmentApp.controller;

import com.Guddu.DoctorAppointmentApp.model.Appointment;
import com.Guddu.DoctorAppointmentApp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;



    @GetMapping("appointment")
    public List<Appointment> getAllAppointment(){
        return appointmentService.getAllAppointment();
    }
}
