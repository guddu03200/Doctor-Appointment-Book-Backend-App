package com.Guddu.DoctorAppointmentApp.service;

import com.Guddu.DoctorAppointmentApp.model.AuthenticationToken;
import com.Guddu.DoctorAppointmentApp.repo.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthTokenRepo authTokenRepo;
    public boolean authenticate(String email, String authToken){
        AuthenticationToken authenticationToken = authTokenRepo.findFirstByTokenValue(authToken);

        if(authenticationToken == null){
            return false;
        }
        String tokenConnectedEmail = authenticationToken.getPatient().getPatientEmail();

        return tokenConnectedEmail.equals(email);

    }
}
