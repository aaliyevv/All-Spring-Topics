package com.ltc.pagination.repo;

import com.ltc.pagination.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepo extends JpaRepository<PatientEntity, Long> {

    @Query("SELECT p FROM PatientEntity p JOIN p.appointments a WHERE a.doctor.id = :doctorId")
    List<PatientEntity> findByDoctorId(@Param("doctorId") Long doctorId);

}

