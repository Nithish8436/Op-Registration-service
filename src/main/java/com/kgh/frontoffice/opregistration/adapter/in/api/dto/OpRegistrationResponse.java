package com.kgh.frontoffice.opregistration.adapter.in.api.dto;

import com.kgh.frontoffice.opregistration.domain.Gender;
import com.kgh.frontoffice.opregistration.domain.MaritalStatus;

import java.time.LocalDate;



public record OpRegistrationResponse(
            String title,
            String patientName,
            String mobileNumber,
            String email,
            Gender gender,
            Integer age,
            LocalDate dateOfBirth,
            MaritalStatus maritalStatus) {

}
