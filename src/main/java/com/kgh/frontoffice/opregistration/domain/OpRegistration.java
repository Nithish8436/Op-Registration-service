package com.kgh.frontoffice.opregistration.domain;

public record OpRegistration(PatientIdentity patientIdentity,ContactInfo contactInfo,Demographics demographics) {

    public OpRegistration{
        if(patientIdentity==null){
            throw new InvalidOpRegistrationException("Patient Name is required");
        }

        if(contactInfo==null){
            throw new InvalidOpRegistrationException("Mobile No is required");
        }

        if(demographics==null){
            throw new InvalidOpRegistrationException("Gender, Age/DOB and Marital Status are required");
        }
    }

    public static OpRegistration of(PatientIdentity patientIdentity,ContactInfo contactInfo,Demographics demographics){
        return new OpRegistration(patientIdentity, contactInfo, demographics);
    }
}