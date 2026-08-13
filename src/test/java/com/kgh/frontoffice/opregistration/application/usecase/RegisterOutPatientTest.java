//package com.kgh.frontoffice.opregistration.application.usecase;
//
//import java.time.LocalDate;
//
//import com.kgh.frontoffice.opregistration.adapter.out.persistence.PatientJpaRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.kgh.frontoffice.opregistration.application.RegisterOutpatientCommand;
////import com.kgh.frontoffice.opregistration.application.port.out.PatientRepository;
//import com.kgh.frontoffice.opregistration.domain.Gender;
//import com.kgh.frontoffice.opregistration.domain.InvalidOpRegistrationException;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//class RegisterOutpatientTest {

//    @Mock
//    private PatientRepository patientRepository;
//
//    @Test
//    void rejectsCommandWithMissingMandatoryField() {
//        RegisterOutpatient registerOutpatient = new RegisterOutpatient(patientRepository);
//
//        RegisterOutpatientCommand command = new RegisterOutpatientCommand(
//                "Mr", " ",                      // salutation, patientName (BLANK — the missing field)
//                "9876543210", "a@b.com",
//                Gender.MALE, LocalDate.of(1990, 1, 1), "Single");
//
//        assertThatThrownBy(() -> registerOutpatient.handle(command))
//                .isInstanceOf(InvalidOpRegistrationException.class);
//
//        verify(patientRepository, never()).save(any());
//    }
//}