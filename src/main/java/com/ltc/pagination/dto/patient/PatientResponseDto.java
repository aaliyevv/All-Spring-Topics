package com.ltc.pagination.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Long doctorId;

    public PatientResponseDto(
            Long id, String fullName, String email, String phoneNumber) {
    }
}
