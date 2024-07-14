package edu.university_connect.model.contract.request.profile;

import edu.university_connect.domain.AcademicAchievement;
import edu.university_connect.domain.ExtraCurricularActivity;
import edu.university_connect.domain.Interest;
import lombok.Data;

import java.util.Set;

@Data
public class ProfileRequest {

    private Set<AcademicAchievement> achievements;

    private Set<Interest> interests;

    private Set<ExtraCurricularActivity> extraCurricularActivities;
}
