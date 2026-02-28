package com.ltc.pagination.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate appointmentData;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    //FK
    @ManyToOne
    @JoinColumn(name = "dostor_id")
    private DoctorEntity doctor;

    // Appointment is the owner side of the relationship (because it has foreign keys).

}
