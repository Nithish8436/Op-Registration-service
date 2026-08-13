package com.kgh.frontoffice.opregistration.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OpRegistrationTest {

    @Test
    void rejectsMissingPatientIdentity(){
        ContactInfo contactInfo=ContactInfo.of("9383848291","a@b.com");
        Demographics demographics=Demographics.of(Gender.MALE, LocalDate.of(1990, 1, 1), "Single");
        assertThatThrownBy(() -> OpRegistration.of(null, contactInfo, demographics))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }
}
