package com.kgh.frontoffice.opregistration.application.port.out;

import com.kgh.frontoffice.opregistration.domain.OpRegistration;

public interface Patients {

    OpRegistration save(OpRegistration opRegistration);
}
