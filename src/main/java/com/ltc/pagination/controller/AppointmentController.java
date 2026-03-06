package com.ltc.pagination.controller;

import com.ltc.pagination.dto.appointment.AppointmentResponseDto;
import com.ltc.pagination.entity.AppointmentEntity;
import com.ltc.pagination.service.AppointmentService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment (@RequestParam LocalDate appointmentDate,
                                                                     @PathVariable Long patientId,
                                                                     @PathVariable Long doctorId) {

        AppointmentResponseDto appointment =
                appointmentService.createAppointment(patientId, doctorId, appointmentDate);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDto>> getAll(Pageable pageable) {

        Page<AppointmentResponseDto> appointments = appointmentService.getAll(pageable);
        return ResponseEntity.ok(appointments);
    }

    