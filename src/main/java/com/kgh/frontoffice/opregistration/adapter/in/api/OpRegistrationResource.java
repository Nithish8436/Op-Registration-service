package com.kgh.frontoffice.opregistration.adapter.in.api;

import com.kgh.frontoffice.opregistration.adapter.in.api.dto.OpRegistrationResponse;
import com.kgh.frontoffice.opregistration.adapter.in.api.dto.RegisterOutpatientRequest;
import com.kgh.frontoffice.opregistration.application.usecase.RegisterOutpatient;
import com.kgh.frontoffice.opregistration.domain.OpRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class OpRegistrationResource {

    private RegisterOutpatient registerOutpatient;

    private OpRegistrationApiMapper mapper;

    public OpRegistrationResource(RegisterOutpatient registerOutpatient,OpRegistrationApiMapper mapper) {
        this.registerOutpatient = registerOutpatient;
        this.mapper=mapper;
    }

    @PostMapping("/op-register")
    public ResponseEntity<OpRegistrationResponse> register(@RequestBody RegisterOutpatientRequest registerOutpatientRequest) {
        OpRegistration opRegistration=registerOutpatient.handle(mapper.toCommand(registerOutpatientRequest));
        return ResponseEntity.ok(mapper.toResponse(opRegistration));
    }
}