package com.kgh.frontoffice.opregistration.application.port.out;

import com.kgh.frontoffice.opregistration.application.usecase.RegisterOutpatient;
import com.kgh.frontoffice.opregistration.domain.OpRegistration;

interface PatientRepository {
    void save(OpRegistration opRegistration);

}
