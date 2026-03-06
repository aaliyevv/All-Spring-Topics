package com.ltc.pagination.controller;

import com.ltc.pagination.dto.doctor.DoctorRequestDto;
import com.ltc.pagination.dto.doctor.DoctorResponseDto;
import com.ltc.pagination.service.DoctorService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<Page<DoctorResponseDto>> getAllDoctors (Pageable pageable) {

        Page<DoctorResponseDto> doctors = doctorService.getAllDoctors(pageable);
        return ResponseEntity.ok(doctors);
    }