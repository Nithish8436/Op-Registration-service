package com.kgh.frontoffice.opregistration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;


public class ContactInfoTest {

    @Test
    void rejectBlankMobileNumber(){
        assertThatThrownBy(()->ContactInfo.of(" ","a@b.com"))
                .isInstanceOf(InvalidOpRegistrationException.class)
                .hasMessageContaining("Mobile No");
    }

    @Test
    void allMandatoryContactInfoPresent(){
        ContactInfo contactInfo=ContactInfo.of("1234567890","a@b.com");
        assertThat(contactInfo).isEqualTo(new ContactInfo("1234567890","a@b.com"));
    }
}
