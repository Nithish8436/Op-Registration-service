package com.kgh.frontoffice.opregistration.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class PatientIdentityTest {

    @Test
    void rejectBlankPatientName(){
        assertThatThrownBy(()->PatientIdentity.of("Mr"," "))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectBlankTitle(){
        assertThatThrownBy(()->PatientIdentity.of(" ","Ram"))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void allMandatoryPatientIdentityFieldsPresent(){
        PatientIdentity patientIdentity=PatientIdentity.of("Mr.","Raj");
        assertThat(patientIdentity).isEqualTo(new PatientIdentity("Mr.","Raj"));
    }
}
