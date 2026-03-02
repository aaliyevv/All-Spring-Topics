package com.ltc.pagination.dto.doctor;

import com.ltc.pagination.enumaration.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDto {

    private Long id;
    private String fullName;
    private Specialization specialization;
    private boolean available;

}