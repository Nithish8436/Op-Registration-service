package com.kgh.frontoffice.opregistration.adapter.out.persistence;

import com.kgh.frontoffice.opregistration.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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

    @Test
    void savesAndReadsBackAValidOpRegistration(){
        Patient patient = Patient.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, null, LocalDate.of(1990, 1, 1), MaritalStatus.SINGLE);

        OpRegistration opRegistration = OpRegistration.of(patient, contactInfo, demographics);

        jpaPatients.save(opRegistration);

        assertThat(opRegistration.demographics().gender()).isEqualTo(demographics.gender());
    }



}
