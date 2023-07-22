package com.Guddu.DoctorAppointmentApp.service;

import com.Guddu.DoctorAppointmentApp.model.Appointment;
import com.Guddu.DoctorAppointmentApp.model.AuthenticationToken;
import com.Guddu.DoctorAppointmentApp.model.Patient;
import com.Guddu.DoctorAppointmentApp.model.dto.SignInInput;
import com.Guddu.DoctorAppointmentApp.model.dto.SignUpOutput;
import com.Guddu.DoctorAppointmentApp.repo.IAppointmentRepo;
import com.Guddu.DoctorAppointmentApp.repo.IAuthTokenRepo;
import com.Guddu.DoctorAppointmentApp.repo.IDoctorRepo;
import com.Guddu.DoctorAppointmentApp.repo.IPatientRepo;
import com.Guddu.DoctorAppointmentApp.service.utility.EmailHandler;
import com.Guddu.DoctorAppointmentApp.service.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatientRepo patientRepo;

    @Autowired
    IDoctorRepo doctorRepo;

    @Autowired
    IAuthTokenRepo authTokenRepo;

    @Autowired
   AppointmentService appointmentService;

    public SignUpOutput signUpPatient(Patient patient) throws NoSuchAlgorithmException {
        boolean singUpStatus = true;
        String signUpStatusMessage = null;


        String newEmail = patient.getPatientEmail();


        if (newEmail == null) {
            signUpStatusMessage = "Invalid email";
            singUpStatus = false;
            return new SignUpOutput(singUpStatus, signUpStatusMessage);

        }

        //check if this patient email is already exist
        Patient existingPatient = patientRepo.findFirstByPatientEmail(newEmail);

        if (existingPatient != null) {
            signUpStatusMessage = "Email already registerd";
            singUpStatus = false;
            return new SignUpOutput(singUpStatus, signUpStatusMessage);
        }

        //hash the password : encrypt the password
        String encryptedPassword = PasswordEncrypter.encryptPassword(patient.getPatientPassword());

        //save the patient with the new encrypted password
        patient.setPatientPassword(encryptedPassword);
        patientRepo.save(patient);


        return new SignUpOutput(singUpStatus, "Patient registerd successfully");


    }


    public List<Patient> findAllPatient() {
        return patientRepo.findAll();
    }

    public String signInPatient(SignInInput signInInput) {
        String signInStatusMessage = null;
        boolean singInStatus = true;

        String signInEmail = signInInput.getEmail();


        if (signInEmail == null) {
            signInStatusMessage = "Invalid email";
            singInStatus = false;
        }

        //check if this patient email is already exist
        Patient existingPatient = patientRepo.findFirstByPatientEmail(signInEmail);

        if (existingPatient == null) {
            signInStatusMessage = "Email not registerd";
            singInStatus = false;
        }


        //match password

        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if (existingPatient.getPatientPassword().equals(encryptedPassword)) {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken = new AuthenticationToken(existingPatient);
                authTokenRepo.save(authToken);

                EmailHandler.sendEmail("guddukumar032002@gmail.com", "email testing", authToken.getTokenValue());
                return "Token sent to your email";
            } else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        } catch (Exception e) {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }

    // todo : create sign out
    public boolean scheduleAppointment(Appointment appointment, String email, String token) {
        //id of doctor
        Long doctorId = appointment.getDoctor().getDoctorId();
        boolean isDoctorValid = doctorRepo.existsById(doctorId);

        //id of patient
        Long patientId = appointment.getPatient().getPatientId();
        boolean isPatientValid = patientRepo.existsById(patientId);

        if (isDoctorValid && isPatientValid) {
           appointmentService.save(appointment);
           return true;
        }
        else{
            return false;
        }
    }

    public void cancelAppointment(String email, String token) {

        //email -> patient -> appointment
        Patient patient = patientRepo.findFirstByPatientEmail(email);
         Appointment appointment = appointmentService.getAllAppointmentForPatient(patient);

         appointmentService.cancelAppointment(appointment);

    }

    public String signOutPatient(String email) {
        Patient patient = patientRepo.findFirstByPatientEmail(email);
        authTokenRepo.delete(authTokenRepo.findFirstByPatient(patient));

        return "patient signOut successfully";
    }
}

