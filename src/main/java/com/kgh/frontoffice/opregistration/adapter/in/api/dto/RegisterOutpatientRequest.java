package com.kgh.frontoffice.opregistration.adapter.in.api.dto;

import java.time.LocalDate;

import com.kgh.frontoffice.opregistration.domain.Gender;
import com.kgh.frontoffice.opregistration.domain.MaritalStatus;

public record RegisterOutpatientRequest(
        String title,
        String patientName,
        String mobileNumber,
        String email,
        Gender gender,
        Integer age,
        LocalDate dateOfBirth,
        MaritalStatus maritalStatus) {
}