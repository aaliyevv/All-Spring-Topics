package com.ltc.pagination.entity;

import com.ltc.pagination.enumaration.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)  // .ORDINAL returns 0, 1
    private Specialization specialization;

    private boolean available;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)

    private List<AppointmentEntity> appointments;

}
