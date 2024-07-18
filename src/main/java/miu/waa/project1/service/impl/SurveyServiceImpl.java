package miu.waa.project1.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import miu.waa.project1.dto.SurveyDTO;
import miu.waa.project1.model.Survey;
import miu.waa.project1.model.User;
import miu.waa.project1.repository.SurveyRepository;
import miu.waa.project1.service.SurveyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Setter
@Getter
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final UserServiceImpl userService;

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id).orElse(null);
    }

    @Override
    public void createSurvey(SurveyDTO s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new RuntimeException("Invalid token or user not found");
        }
        User user = userService.getUserByEmail(userDetails.getUsername());
        Survey survey = new Survey();
        survey.setDate(LocalDate.now());
        survey.setName(s.getName());
        survey.setComment(s.getComment());
        survey.setTitle(s.getTitle());
        survey.setDescription(s.getDescription());
        survey.setRate(s.getRate());
        survey.setEmail(user.getEmail());
        survey.setUser(user);
        surveyRepository.save(survey);
    }

    @Override
    public Survey updateSurvey(Long id, SurveyDTO s) {
        Survey survey = surveyRepository.findById(id).orElse(null);
        if (survey != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
                throw new RuntimeException("Invalid token or user not found");
            }
            User user = userService.getUserByEmail(userDetails.getUsername());
            survey.setComment(s.getComment());
            survey.setTitle(s.getTitle());
            survey.setDescription(s.getDescription());
            survey.setRate(s.getRate());
            survey.setEmail(user.getEmail());
            survey.setUser(user);
        }
        return surveyRepository.save(survey);
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public Survey getSurveysByUserId(Long userId) {
        return surveyRepository.findAllByUserId(userId);
    }
}
