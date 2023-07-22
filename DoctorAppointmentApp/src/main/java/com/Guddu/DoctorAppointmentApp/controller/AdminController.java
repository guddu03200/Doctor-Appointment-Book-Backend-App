package com.Guddu.DoctorAppointmentApp.controller;

import com.Guddu.DoctorAppointmentApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
}
