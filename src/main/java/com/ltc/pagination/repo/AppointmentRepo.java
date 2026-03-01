package com.ltc.pagination.repo;

import com.ltc.pagination.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AppointmentRepo extends JpaRepository<AppointmentEntity, Long> {

    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);
}