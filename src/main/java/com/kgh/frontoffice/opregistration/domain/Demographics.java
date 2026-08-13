package com.kgh.frontoffice.opregistration.domain;

import java.time.LocalDate;
import java.util.Objects;

public record Demographics(Gender gender, LocalDate dateOfBirth, String maritalStatus) {

    public Demographics {
        if (gender == null) {
            throw new InvalidOpRegistrationException("Gender is required");
        }

        if(dateOfBirth==null){
            throw new InvalidOpRegistrationException("Age/DOB is required");
        }

        if(maritalStatus==null||maritalStatus.isBlank()){
            throw new InvalidOpRegistrationException("Marital Status is required");

        }
    }

    public static Demographics of(Gender gender, LocalDate dateOfBirth, String maritalStatus) {
        return new Demographics(gender, dateOfBirth, maritalStatus);
    }
}
