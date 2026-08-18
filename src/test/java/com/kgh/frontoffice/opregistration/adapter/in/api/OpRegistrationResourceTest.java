package com.kgh.frontoffice.opregistration.adapter.in.api;

import com.kgh.frontoffice.opregistration.application.usecase.RegisterOutpatient;
import com.kgh.frontoffice.opregistration.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OpRegistrationResource.class)
@Import(OpRegistrationApiMapper.class)
public class OpRegistrationResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterOutpatient registerOutpatient;

    @Test
    void rejectsRequestWithMissingMandatoryField() throws Exception {
        when(registerOutpatient.handle(any())).thenThrow(new InvalidOpRegistrationException("Mandatory field missing..."));
        String requestBody = """
                {
                  "title": "Mr",
                  "patientName": "",
                  "mobileNumber": "9876543210",
                  "email": "a@b.com",
                  "gender": "MALE",
                  "age": null,
                  "dateOfBirth": "1990-01-01",
                  "maritalStatus": "SINGLE"
                }
                """;

        mockMvc.perform(post("/op-register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Mandatory field missing..."));
    }

    @Test
    void acceptRequestWithAllMandatoryFields() throws Exception {

        Patient patient = Patient.of("Mr", "Ram");
        ContactInfo contactInfo = ContactInfo.of("9876543210", "a@b.com");
        Demographics demographics = Demographics.of(Gender.MALE, null, LocalDate.of(1990, 1, 1), MaritalStatus.SINGLE);
        OpRegistration opRegistration = OpRegistration.of(patient, contactInfo, demographics);

        when(registerOutpatient.handle(any())).thenReturn(opRegistration);
        String requestBody = """
                {
                  "title": "Mr",
                  "patientName": "Abi",
                  "mobileNumber": "9876543210",
                  "email": "a@b.com",
                  "gender": "MALE",
                  "age": null,
                  "dateOfBirth": "1990-01-01",
                  "maritalStatus": "SINGLE"
                }
                """;

        mockMvc.perform(post("/op-register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mr"))
                .andExpect(jsonPath("$.patientName").value("Ram"))
                .andExpect(jsonPath("$.mobileNumber").value("9876543210"))
                .andExpect(jsonPath("$.gender").value("MALE"))
                .andExpect(jsonPath("$.dateOfBirth").value("1990-01-01"))
                .andExpect(jsonPath("$.maritalStatus").value("SINGLE"));
    }
}
