package com.kgh.frontoffice.opregistration.adapter.in.api;

import com.kgh.frontoffice.opregistration.adapter.in.api.dto.OpRegistrationResponse;
import com.kgh.frontoffice.opregistration.adapter.in.api.dto.RegisterOutpatientRequest;
import com.kgh.frontoffice.opregistration.application.RegisterOutpatientCommand;
import com.kgh.frontoffice.opregistration.domain.OpRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OpRegistrationApiMapper {
    public RegisterOutpatientCommand toCommand(RegisterOutpatientRequest request) {
        return new RegisterOutpatientCommand(
                request.title(),
                request.patientName(),
                request.mobileNumber(),
                request.email(),
                request.gender(),
                request.age(),
                request.dateOfBirth(),
                request.maritalStatus());
    }

    public OpRegistrationResponse toResponse(OpRegistration opRegistration) {
        return new OpRegistrationResponse(
                opRegistration.patient().title(),
                opRegistration.patient().patientName(),
                opRegistration.contactInfo().mobileNumber(),
                opRegistration.contactInfo().email(),
                opRegistration.demographics().gender(),
                opRegistration.demographics().age(),
                opRegistration.demographics().dateOfBirth(),
                opRegistration.demographics().maritalStatus());
    }
}
