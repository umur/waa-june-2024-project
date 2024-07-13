package edu.university_connect.model.contract.dto;

import edu.university_connect.domain.AcademicAchievement;
import edu.university_connect.domain.ExtraCurricularActivity;
import edu.university_connect.domain.Interest;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProfileDto {
    private Long id;

    private Set<AcademicAchievement> achievements;

    private Set<Interest> interests;

    private Set<ExtraCurricularActivity> extraCurricularActivities;

    public ProfileDto() {
        achievements=new HashSet<>();
        interests=new HashSet<>();
        extraCurricularActivities=new HashSet<>();
    }
}
