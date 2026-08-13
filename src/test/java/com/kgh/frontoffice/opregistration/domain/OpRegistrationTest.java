package com.kgh.frontoffice.opregistration.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OpRegistrationTest {

    @Test
    void rejectsMissingPatientIdentity() {
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, LocalDate.of(1990, 1, 1), "Single");
        assertThatThrownBy(() -> OpRegistration.of(null, contactInfo, demographics))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectsMissingContactInfo() {
        PatientIdentity patientIdentity = PatientIdentity.of("Mr", "Ram");
        Demographics demographics = Demographics.of(Gender.MALE, LocalDate.of(1990, 1, 1), "Single");
        assertThatThrownBy(() -> OpRegistration.of(patientIdentity, null, demographics))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectsMissingDemographics(){
        PatientIdentity patientIdentity = PatientIdentity.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        assertThatThrownBy(() -> OpRegistration.of(patientIdentity, contactInfo, null))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void createOpRegistrationWhenAllPartsPresent(){
        PatientIdentity patientIdentity = PatientIdentity.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9383848291", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, LocalDate.of(1990, 1, 1), "Single");

        OpRegistration opRegistration=OpRegistration.of(patientIdentity, contactInfo, demographics);

        assertThat(opRegistration.patientIdentity().equals(patientIdentity));
        assertThat(opRegistration.contactInfo().equals(contactInfo));
        assertThat(opRegistration.demographics().equals(demographics));

    }
}
