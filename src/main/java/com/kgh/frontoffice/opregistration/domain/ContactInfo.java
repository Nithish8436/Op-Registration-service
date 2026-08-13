package com.kgh.frontoffice.opregistration.domain;

import java.util.Objects;

public record ContactInfo(String mobileNumber, String email) {

    public ContactInfo{
        Objects.requireNonNull(mobileNumber,"mobileNumber must not be null");
        if(mobileNumber.isBlank()){
            throw new InvalidOpRegistrationException("Mobile No is required");
        }
    }

    public static ContactInfo of(String mobileNumber,String email){
        return new ContactInfo(mobileNumber, email);
    }

}