package com.kgh.frontoffice.opregistration.bootstrap;

import com.kgh.frontoffice.opregistration.application.port.out.Patients;
import com.kgh.frontoffice.opregistration.application.usecase.RegisterOutpatient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RegisterOutpatient registerOutpatient(Patients patients) {
        return new RegisterOutpatient(patients);
    }
}