package com.kgh.frontoffice.opregistration.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.kgh.frontoffice.opregistration.application.port.out.Patients;
import com.kgh.frontoffice.opregistration.domain.OpRegistration;

@Component
public class JpaPatients implements Patients {

    private final PatientJpaRepository repository;
    private final OpRegistrationPersistenceMapper mapper;

    public JpaPatients(PatientJpaRepository repository, OpRegistrationPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OpRegistration save(OpRegistration opRegistration) {
        return mapper.toDomain(repository.save(mapper.toNewEntity(opRegistration)));
    }
}
