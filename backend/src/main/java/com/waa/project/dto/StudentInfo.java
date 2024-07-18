package com.waa.project.dto;

import com.waa.project.contracts.MajorResponse;
import com.waa.project.enums.GenderType;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class StudentInfo {
    private String        username;
    private String        firstName;
    private String        lastName;
    private String        email;
    private String        picture;
}
