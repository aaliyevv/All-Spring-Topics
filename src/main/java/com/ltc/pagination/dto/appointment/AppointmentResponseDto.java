package com.ltc.pagination.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {

    private Long id;
    private LocalDate appointmentDate;
    private Long patientId;
    private Long doctorId;

}