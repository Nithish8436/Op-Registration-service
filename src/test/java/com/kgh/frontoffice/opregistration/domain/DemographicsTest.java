package com.kgh.frontoffice.opregistration.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DemographicsTest {

    @Test
    void rejectsMissingGender() {
        assertThatThrownBy(() -> Demographics.of(null, LocalDate.of(1990, 1, 1), "Single"))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectsMissingDateOfBirth() {
        assertThatThrownBy(() -> Demographics.of(Gender.MALE, null, "Single"))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectsMissingMaritalStatus(){
        assertThatThrownBy(()->Demographics.of(Gender.MALE,LocalDate.of(1990,1,1)," "))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }


    @Test
    void allMandatoryDemographicsDetailsPresent(){
        Demographics demographics=Demographics.of(Gender.MALE,LocalDate.of(1990,1,1),"Single");
        assertThat(demographics).isEqualTo(new Demographics(Gender.MALE,LocalDate.of(1990,1,1),"Single"));
    }


}
