package com.kgh.frontoffice.opregistration.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.kgh.frontoffice.opregistration.domain.ContactInfo;
import com.kgh.frontoffice.opregistration.domain.Demographics;
import com.kgh.frontoffice.opregistration.domain.OpRegistration;
import com.kgh.frontoffice.opregistration.domain.Patient;

@Component
public class OpRegistrationPersistenceMapper {

    public PatientEntity toNewEntity(OpRegistration opRegistration) {
        return new PatientEntity(
                opRegistration.patient().title(),
                opRegistration.patient().patientName(),
                opRegistration.contactInfo().mobileNumber(),
                opRegistration.contactInfo().email(),
                opRegistration.demographics().gender(),
                opRegistration.demographics().age(),
                opRegistration.demographics().dateOfBirth(),
                opRegistration.demographics().maritalStatus());
    }

    public OpRegistration toDomain(PatientEntity entity) {
        Patient patient = Patient.of(entity.getTitle(), entity.getPatientName());
        ContactInfo contactInfo = ContactInfo.of(entity.getMobileNumber(), entity.getEmail());
        Demographics demographics = Demographics.of(
                entity.getGender(), entity.getAge(), entity.getDateOfBirth(), entity.getMaritalStatus());

        return OpRegistration.of(patient, contactInfo, demographics);
    }
}
