package com.Guddu.DoctorAppointmentApp.controller;

import com.Guddu.DoctorAppointmentApp.model.Appointment;
import com.Guddu.DoctorAppointmentApp.model.Patient;
import com.Guddu.DoctorAppointmentApp.model.dto.SignInInput;
import com.Guddu.DoctorAppointmentApp.model.dto.SignUpOutput;
import com.Guddu.DoctorAppointmentApp.service.AuthenticationService;
import com.Guddu.DoctorAppointmentApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("patient/signUp")
    public SignUpOutput signUpPatient(@RequestBody Patient patient) throws NoSuchAlgorithmException {
      return   patientService.signUpPatient(patient);
    }

    @PostMapping("patient/signIn")
    public String signInPatient(@RequestBody SignInInput signInInput){
        return patientService.signInPatient(signInInput);
    }

    @DeleteMapping("patient/signOut")
    public String signOutPatient(String email, String token){
        if(authenticationService.authenticate(email, token)){
            return patientService.signOutPatient(email);
        }else {
            return "SignOut not allowed for non authenticated user";
        }
    }

    @GetMapping("patient")
    public List<Patient> findAllPatient(){
        return patientService.findAllPatient();
    }


    @PostMapping("appointment/schedule")
    public String scheduleAppointment(@RequestBody Appointment appointment, String email, String token){
        if(authenticationService.authenticate(email, token)){
          boolean status =  patientService.scheduleAppointment(appointment, email, token);
            return status?"appointment schedule":"error occurred during schedule appointment";
        }else {
            return "Scheduling failed because invalid authentication";
        }

    }

    @DeleteMapping("appointment/cancel")
    public String cancelAppointment(String email, String token){
        if(authenticationService.authenticate(email, token)){
            patientService.cancelAppointment(email, token);
            return "Canceled Appointment Successfully";
        }else {
            return "Scheduling failed because invalid authentication";
        }

    }
}



