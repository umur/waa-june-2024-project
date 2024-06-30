package miu.waa.project1.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import miu.waa.project1.model.Survey;
import miu.waa.project1.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping("")
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

    @GetMapping("/user/{userId}")
    public Survey getSurveysByUserId(@PathVariable Long userId) {
        return surveyService.getSurveysByUserId(userId);
    }

    @PostMapping("")
    public void createSurvey(@RequestBody Survey survey) {
        surveyService.createSurvey(survey);
    }

    @PutMapping("/{id}")
    public void updateSurvey(@PathVariable Long id,@RequestBody Survey survey) {
        surveyService.updateSurvey(id, survey);
    }
}
