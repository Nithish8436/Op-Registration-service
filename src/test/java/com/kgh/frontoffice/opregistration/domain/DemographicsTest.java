package com.kgh.frontoffice.opregistration.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DemographicsTest {

    @Test
    void rejectsMissingGender() {
        assertThatThrownBy(() -> Demographics.of(null,null, LocalDate.of(1990, 1, 1), MaritalStatus.SINGLE))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectsMissingAgeAndDateOfBirth() {
        assertThatThrownBy(() -> Demographics.of(Gender.MALE, null,null, MaritalStatus.SINGLE))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void rejectsMissingMaritalStatus(){
        assertThatThrownBy(()->Demographics.of(Gender.MALE,null,LocalDate.of(1990,1,1),null))
                .isInstanceOf(InvalidOpRegistrationException.class);
    }

    @Test
    void acceptsAgeAloneWithoutDateOfBirth() {
        Demographics demographics = Demographics.of(Gender.MALE, 30, null, MaritalStatus.SINGLE);

        assertThat(demographics.age()).isEqualTo(30);
    }


    @Test
    void allMandatoryDemographicsDetailsPresent(){
        Demographics demographics=Demographics.of(Gender.MALE,null, LocalDate.of(1990,1,1),MaritalStatus.SINGLE);
        assertThat(demographics).isEqualTo(new Demographics(Gender.MALE,null,LocalDate.of(1990,1,1),MaritalStatus.SINGLE));
    }


}
