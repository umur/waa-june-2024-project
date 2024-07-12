package com.waa.project.contracts;

import com.waa.project.enums.GenderType;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class StudentResponse {
    private String        username;
    private String        firstName;
    private String        lastName;
    private String        email;
    private LocalDate     birthDate;
    private GenderType    genderType;
    private String        studentCode;
    private String        academicYears;
    private String        picture;
    private Set<String>   achievements;
    private Set<String>   interest;
    private Set<String>   extraActivities;
    private MajorResponse major;
}
