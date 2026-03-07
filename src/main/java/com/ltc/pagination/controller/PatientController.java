package com.ltc.pagination.controller;


import com.ltc.pagination.dto.patient.PatientRequestDto;
import com.ltc.pagination.dto.patient.PatientResponseDto;
import com.ltc.pagination.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto patientRequestDto) {

        PatientResponseDto savedPatient = patientService.createPatient(patientRequestDto);
        return ResponseEntity.ok(savedPatient);

    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDto>> getAll(Pageable pageable) {

        Page<PatientResponseDto> patients = patientService.getAllPatients(pageable);
        return ResponseEntity.ok(patients);

    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PatientResponseDto>> getPatientByDoctorId(@RequestParam Long doctorId) {

        List<PatientResponseDto> patient = patientService.getPatientByDoctorId(doctorId);
        return ResponseEntity.ok(patient);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {

        PatientResponseDto patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatientById(
            @RequestParam Long id, @RequestBody PatientRequestDto patientRequestDto) {

        PatientResponseDto updated = patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id) {

        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }

}