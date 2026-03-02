package com.ltc.pagination.dto.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDto {

    @Size(min = 8, max = 50)
    private String fullName;

    @Email
    private String email;

    @NotBlank(message = "must not be blank")
    private String phoneNumber;

    private Long doctorId;  // optional FK

}
