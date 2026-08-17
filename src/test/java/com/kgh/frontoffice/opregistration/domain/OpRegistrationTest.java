package com.kgh.frontoffice.opregistration.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OpRegistrationTest {

    @Test
    void rejectsMissingPatient() {
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, null, LocalDate.of(1990, 1, 1), MaritalStatus.SINGLE);
        assertThatThrownBy(() -> OpRegistration.of(null, contactInfo, demographics))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectsMissingContactInfo() {
        Patient patient = Patient.of("Mr", "Ram");
        Demographics demographics = Demographics.of(Gender.MALE, null, LocalDate.of(1990, 1, 1), MaritalStatus.SINGLE);
        assertThatThrownBy(() -> OpRegistration.of(patient, null, demographics))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectsMissingDemographics() {
        Patient patient = Patient.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        assertThatThrownBy(() -> OpRegistration.of(patient, contactInfo, null))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void createOpRegistrationWhenAllPartsPresent() {
        Patient patient = Patient.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, null, LocalDate.of(1990, 1, 1), MaritalStatus.SINGLE);

        OpRegistration opRegistration = OpRegistration.of(patient, contactInfo, demographics);

        assertThat(opRegistration.patient()).isEqualTo(patient);
        assertThat(opRegistration.contactInfo()).isEqualTo(contactInfo);
        assertThat(opRegistration.demographics()).isEqualTo(demographics);
    }
}
