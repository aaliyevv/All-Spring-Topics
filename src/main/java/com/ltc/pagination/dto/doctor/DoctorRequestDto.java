package com.ltc.pagination.dto.doctor;

import com.ltc.pagination.enumaration.Specialization;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorRequestDto {

    @Size(min = 8, max = 50)
    private String fullName;
    private Specialization specialization;
    private boolean available;

}