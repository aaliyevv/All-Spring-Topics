package com.ltc.pagination.dto.doctor;

import com.ltc.pagination.enumaration.Specialization;
import lombok.Data;

@Data
public class DoctorRequestDto {

    private String fullName;
    private Specialization specialization;
    private boolean available;

}