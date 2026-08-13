package com.kgh.frontoffice.opregistration.domain;

public final class InvalidOpRegistrationException extends BusinessException{
    public InvalidOpRegistrationException(String message){
        super(ClientError.VALIDATION,"OP-REGISTRATION-INVALID",message);
    }
}



