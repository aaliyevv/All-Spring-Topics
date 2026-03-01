package com.ltc.pagination.repo;

import com.ltc.pagination.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface DoctorRepo extends JpaRepository<DoctorEntity, Long> {

}