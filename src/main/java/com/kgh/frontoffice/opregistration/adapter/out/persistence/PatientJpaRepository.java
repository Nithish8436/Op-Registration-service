package com.kgh.frontoffice.opregistration.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientJpaRepository extends JpaRepository<PatientEntity, Long> {
}
