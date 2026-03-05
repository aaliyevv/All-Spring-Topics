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

    public DoctorResponseDto getDoctorById(Long id) {

        DoctorEntity doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found: " + id));
        return new DoctorResponseDto(
                doctor.getId(), doctor.getFullName(), doctor.getSpecialization(), doctor.isAvailable());

    }

    public DoctorResponseDto createDoctor(DoctorRequestDto doctorRequestDto) {

        DoctorEntity doctorEntity = new DoctorEntity();

        doctorEntity.setFullName(doctorRequestDto.getFullName());
        doctorEntity.setSpecialization(doctorRequestDto.getSpecialization());
        doctorEntity.setAvailable(doctorRequestDto.isAvailable());

        DoctorEntity savedDoctor = doctorRepo.save(doctorEntity);

        return new DoctorResponseDto(
                savedDoctor.getId(), savedDoctor.getFullName(), savedDoctor.getSpecialization(), savedDoctor.isAvailable());
    }

    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto doctorRequestDto) {

        DoctorEntity doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found: " + id));

        doctor.setFullName(doctorRequestDto.getFullName());
        doctor.setSpecialization(doctorRequestDto.getSpecialization());
        doctor.setAvailable(doctorRequestDto.isAvailable());

        DoctorEntity updatedDoctor = doctorRepo.save(doctor);

        return new DoctorResponseDto(
                updatedDoctor.getId(), updatedDoctor.getFullName(), updatedDoctor.getSpecialization(), updatedDoctor.isAvailable());
    }

    public void deleteDoctorById(Long id) {

        DoctorEntity doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found: " + id));

        if (!doctor.getAppointments().isEmpty()) {
            throw new DoctorHasAppointmentException("Cannot delete patient with appointments. Patient id: " + id);
        }

        doctorRepo.delete(doctor);
    }
}