package com.kgh.frontoffice.opregistration.domain;

import java.time.LocalDate;
import java.util.Objects;

public record Demographics(Gender gender, Integer age, LocalDate dateOfBirth, MaritalStatus maritalStatus) {

    public Demographics {
        if (gender == null) {
            throw new InvalidOpRegistrationException("Gender is required");
        }

        if(age == null && dateOfBirth == null){
            throw new InvalidOpRegistrationException("Age/DOB is required");
        }

        if(maritalStatus==null){
            throw new InvalidOpRegistrationException("Marital Status is required");

        }
    }

    public static Demographics of(Gender gender, Integer age, LocalDate dateOfBirth, MaritalStatus maritalStatus) {
        return new Demographics(gender, age, dateOfBirth, maritalStatus);
    }
}
