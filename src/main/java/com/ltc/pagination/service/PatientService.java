package com.ltc.pagination.service;

import com.ltc.pagination.dto.patient.PatientRequestDto;
import com.ltc.pagination.dto.patient.PatientResponseDto;
import com.ltc.pagination.entity.DoctorEntity;
import com.ltc.pagination.entity.PatientEntity;
import com.ltc.pagination.exception.DoctorNotFoundException;
import com.ltc.pagination.exception.PatientHasAppointmentsException;
import com.ltc.pagination.exception.PatientNotFoundException;
import com.ltc.pagination.repo.DoctorRepo;
import com.ltc.pagination.repo.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientService {

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    public PatientService(PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {

        PatientEntity patientEntity = new PatientEntity();

        patientEntity.setFullName(patientRequestDto.getFullName());
        patientEntity.setEmail(patientRequestDto.getEmail());
        patientEntity.setPhoneNumber(patientRequestDto.getPhoneNumber());

        PatientEntity db = patientRepo.save(patientEntity);

        return new PatientResponseDto (db.getId(), db.getFullName(), db.getEmail(), db.getPhoneNumber());

    }

    