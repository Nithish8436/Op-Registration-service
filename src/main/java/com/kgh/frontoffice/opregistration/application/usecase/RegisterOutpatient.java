package com.kgh.frontoffice.opregistration.application.usecase;

import com.kgh.frontoffice.opregistration.application.RegisterOutpatientCommand;
import com.kgh.frontoffice.opregistration.application.port.out.Patients;
import com.kgh.frontoffice.opregistration.domain.ContactInfo;
import com.kgh.frontoffice.opregistration.domain.Demographics;
import com.kgh.frontoffice.opregistration.domain.OpRegistration;
import com.kgh.frontoffice.opregistration.domain.Patient;

public class RegisterOutpatient {

    private final Patients patientRepository;

    public RegisterOutpatient(Patients patientRepository) {
        this.patientRepository = patientRepository;
    }

    public OpRegistration handle(RegisterOutpatientCommand command) {
        Patient patient = Patient.of(command.title(), command.patientName());
        ContactInfo contactInfo = ContactInfo.of(command.mobileNumber(), command.email());
        Demographics demographics = Demographics.of(command.gender(), command.age(), command.dateOfBirth(), command.maritalStatus());

        OpRegistration opRegistration = OpRegistration.of(patient, contactInfo, demographics);

        return patientRepository.save(opRegistration);
    }
}
