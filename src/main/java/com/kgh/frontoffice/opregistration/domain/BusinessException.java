package com.kgh.frontoffice.opregistration.domain;

import java.util.Objects;


public abstract class BusinessException extends RuntimeException{

    private final ClientError error;
    private final String code;

    protected BusinessException(ClientError error,String code,String message){
        super(message);
        this.error = Objects.requireNonNull(error, "error must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
    }

    public final ClientError error(){
        return error;
    }

    public final String code(){
        return code;
    }
}