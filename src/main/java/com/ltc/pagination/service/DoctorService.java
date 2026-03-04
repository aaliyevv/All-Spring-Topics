package com.ltc.pagination.service;

import com.ltc.pagination.dto.doctor.DoctorRequestDto;
import com.ltc.pagination.dto.doctor.DoctorResponseDto;
import com.ltc.pagination.entity.DoctorEntity;
import com.ltc.pagination.exception.DoctorHasAppointmentException;
import com.ltc.pagination.exception.DoctorNotFoundException;
import com.ltc.pagination.repo.DoctorRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepo doctorRepo;

    public DoctorService(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public Page<DoctorResponseDto> getAllDoctors(Pageable pageable) {

        return doctorRepo.findAll(pageable).map(
                d -> new DoctorResponseDto(
                        d.getId(),
                        d.getFullName(),
                        d.getSpecialization(),
                        d.isAvailable()
                ));
    }
