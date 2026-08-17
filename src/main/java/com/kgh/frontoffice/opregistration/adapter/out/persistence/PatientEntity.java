package com.kgh.frontoffice.opregistration.adapter.out.persistence;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.kgh.frontoffice.opregistration.domain.Gender;
import com.kgh.frontoffice.opregistration.domain.MaritalStatus;

@Entity
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String patientName;
    private String mobileNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    protected PatientEntity() {
        // JPA requires a no-arg constructor to construct entities via reflection
    }

    public PatientEntity(String title, String patientName, String mobileNumber, String email,
            Gender gender, Integer age, LocalDate dateOfBirth, MaritalStatus maritalStatus) {
        this.title = title;
        this.patientName = patientName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getPatientName() { return patientName; }
    public String getMobileNumber() { return mobileNumber; }
    public String getEmail() { return email; }
    public Gender getGender() { return gender; }
    public Integer getAge() { return age; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public MaritalStatus getMaritalStatus() { return maritalStatus; }
}
