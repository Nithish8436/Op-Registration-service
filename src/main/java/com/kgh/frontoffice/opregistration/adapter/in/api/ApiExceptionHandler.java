package com.kgh.frontoffice.opregistration.adapter.in.api;

import com.kgh.frontoffice.opregistration.application.usecase.RegisterOutpatient;
import com.kgh.frontoffice.opregistration.domain.InvalidOpRegistrationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidOpRegistrationException.class)
    public ResponseEntity<String> handleInvalidOpRegistration(InvalidOpRegistrationException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
