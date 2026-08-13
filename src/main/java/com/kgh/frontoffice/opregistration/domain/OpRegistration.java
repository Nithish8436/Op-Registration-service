package com.kgh.frontoffice.opregistration.domain;

public record OpRegistration(PatientIdentity patientIdentity,ContactInfo contactInfo,Demographics demographics) {

    public OpRegistration{
        if(patientIdentity==null){
            throw new InvalidOpRegistrationException("Patient Name is required");
        }
    }

    public static OpRegistration of(PatientIdentity patientIdentity,ContactInfo contactInfo,Demographics demographics){
        return new OpRegistration(patientIdentity, contactInfo, demographics);
    }
}