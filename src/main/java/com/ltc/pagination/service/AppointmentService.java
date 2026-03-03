package com.ltc.pagination.service;

import com.ltc.pagination.dto.appointment.AppointmentResponseDto;
import com.ltc.pagination.entity.AppointmentEntity;
import com.ltc.pagination.entity.DoctorEntity;
import com.ltc.pagination.entity.PatientEntity;
import com.ltc.pagination.exception.*;
import com.ltc.pagination.repo.AppointmentRepo;
import com.ltc.pagination.repo.DoctorRepo;
import com.ltc.pagination.repo.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;


    public AppointmentService(AppointmentRepo appointmentRepo, DoctorRepo doctorRepo, PatientRepo patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    