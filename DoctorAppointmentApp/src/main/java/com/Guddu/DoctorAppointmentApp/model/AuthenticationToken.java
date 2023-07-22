package com.Guddu.DoctorAppointmentApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDate;

    //mapping
    @OneToOne
    @JoinColumn(name = "fk_patient_id")
    Patient patient;

    public AuthenticationToken(Patient patient){
        this.patient=patient;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDate = LocalDateTime.now();

    }

}
