package com.kgh.frontoffice.opregistration.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PatientTest {

    @Test
    void rejectBlankPatientName() {
        assertThatThrownBy(() -> Patient.of("Mr", " "))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectBlankTitle() {
        assertThatThrownBy(() -> Patient.of(" ", "Ram"))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void allMandatoryPatientFieldsPresent() {
        Patient patient = Patient.of("Mr.", "Raj");
        assertThat(patient).isEqualTo(new Patient("Mr.", "Raj"));
    }
}
