package com.kgh.frontoffice.opregistration.domain;

import java.util.Objects;

public record Patient(String title, String patientName) {

    public Patient {
        Objects.requireNonNull(patientName, "patientName must not be null");
        if (patientName.isBlank()) {
            throw new InvalidOpRegistrationException("Patient Name is required");
        }

        Objects.requireNonNull(title, "title must not be null");
        if (title.isBlank()) {
            throw new InvalidOpRegistrationException("Title is required");
        }
    }

    public static Patient of(String title, String patientName) {
        return new Patient(title, patientName);
    }
}
