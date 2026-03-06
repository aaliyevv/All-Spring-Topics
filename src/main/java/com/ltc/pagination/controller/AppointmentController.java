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

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getById(@PathVariable Long id){

        AppointmentResponseDto appointment = appointmentService.getById(id);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> update(@PathVariable Long id,
                                                         @PathVariable Long patientId,
                                                         @PathVariable Long doctorId,
                                                         @RequestParam LocalDate appointmentDate){

        AppointmentResponseDto updated = appointmentService
                .updateAppointment(id, patientId, doctorId, appointmentDate);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {

        appointmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}