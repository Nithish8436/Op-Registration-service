package com.kgh.frontoffice.opregistration.adapter.out.persistence;

import com.kgh.frontoffice.opregistration.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

@SpringBootTest
@Transactional
@Testcontainers
public class OpRegistrationPersistenceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void datasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private JpaPatients jpaPatients;

    @Autowired
    private PatientJpaRepository patientJpaRepository;

    @Test
    void savesAndReadsBackAValidOpRegistration() {
        Patient patient = Patient.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, null, LocalDate.of(1990, 1, 1), MaritalStatus.SINGLE);

        OpRegistration opRegistration = OpRegistration.of(patient, contactInfo, demographics);

        jpaPatients.save(opRegistration);

        List<PatientEntity> savedRows = patientJpaRepository.findAll();
        assertThat(savedRows).hasSize(1);

        PatientEntity saved = savedRows.get(0);
        assertThat(saved.getTitle()).isEqualTo(opRegistration.patient().title());
        assertThat(saved.getPatientName()).isEqualTo(opRegistration.patient().patientName());
        assertThat(saved.getMobileNumber()).isEqualTo(opRegistration.contactInfo().mobileNumber());
        assertThat(saved.getGender()).isEqualTo(opRegistration.demographics().gender());
        assertThat(saved.getDateOfBirth()).isEqualTo(opRegistration.demographics().dateOfBirth());
        assertThat(saved.getMaritalStatus()).isEqualTo(opRegistration.demographics().maritalStatus());

    }

    @Test
    void savesOpRegistrationWithAgeInsteadOfDateOfBirth() {
        Patient patient = Patient.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, 55, null, MaritalStatus.SINGLE);

        OpRegistration opRegistration = OpRegistration.of(patient, contactInfo, demographics);

        jpaPatients.save(opRegistration);

        assertThat(patientJpaRepository.findAll()).hasSize(1);
        assertThat(patientJpaRepository.findAll().get(0).getAge()).isEqualTo(55);
        assertThat(patientJpaRepository.findAll().get(0).getDateOfBirth()).isNull();

    }

    @Test
    void assignsGeneratedIdOnSave() {
        Patient patient = Patient.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, 55, null, MaritalStatus.SINGLE);

        OpRegistration opRegistration = OpRegistration.of(patient, contactInfo, demographics);

        jpaPatients.save(opRegistration);

        assertThat(patientJpaRepository.findAll()).hasSize(1);
        assertThat(patientJpaRepository.findAll().get(0).getId()).isNotNull();

    }


}
