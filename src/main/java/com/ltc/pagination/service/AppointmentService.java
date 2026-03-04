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

    public AppointmentResponseDto createAppointment(Long patientId, Long doctorId, LocalDate appointmentDate) {

        PatientEntity patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found: " + patientId));

        DoctorEntity doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found: " + doctorId));

        if (!doctor.isAvailable()){
            throw new DoctorNotFoundException("Doctor not available");
        }

        boolean exists = appointmentRepo
                .existsByDoctorIdAndAppointmentDate(doctorId, appointmentDate);

        if (exists){
            throw new AppointmentAlreadyExistsException("Appointment already exists!");
        }

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setAppointmentDate(appointmentDate);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        AppointmentEntity saved = appointmentRepo.save(appointment);

        return new AppointmentResponseDto(
                saved.getId(), saved.getAppointmentDate(), patientId, doctorId);
    }

    public Page<AppointmentResponseDto> getAll(Pageable pageable){

        return appointmentRepo.findAll(pageable).map(
                a -> new AppointmentResponseDto(
                        a.getId(),
                        a.getAppointmentDate(),
                        a.getPatient().getId(),
                        a.getDoctor().getId()
                ));
    }

    public AppointmentResponseDto getById(Long id){

        AppointmentEntity appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found: " + id));

        return new AppointmentResponseDto(
                appointment.getId(),
                appointment.getAppointmentDate(),
                appointment.getPatient().getId(),
                appointment.getDoctor().getId());
    }

    public AppointmentResponseDto updateAppointment(
            Long id, Long patientId, Long doctorId, LocalDate appointmentDate) {

        AppointmentEntity appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found: " + id));

        PatientEntity patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found: " + patientId));

        DoctorEntity doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found: " + doctorId));

        if (!doctor.isAvailable()){
            throw new DoctorNotAvailableException("Doctor not available");
        }

        boolean exists = appointmentRepo
                .existsByDoctorIdAndAppointmentDate(doctorId, appointmentDate);

        if (exists){
            throw new AppointmentAlreadyExistsException("Appointment already exists!");
        }appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);

        AppointmentEntity updated = appointmentRepo.save(appointment);
        return new AppointmentResponseDto(
                updated.getId(),
                updated.getAppointmentDate(),
                patientId,
                doctorId
        );
    }


    public void deleteById(Long id){

        AppointmentEntity appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found: " + id));

        appointmentRepo.delete(appointment);
    }
}