package universityconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import universityconnect.domain.*;
import universityconnect.dto.SurveyDTO;
import universityconnect.service.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyStudentService surveyStudentService;

    @GetMapping
    public List<SurveyDTO> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public SurveyDTO getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<SurveyStudent> getSurveysByStudent(@PathVariable Long studentId) {
        return surveyStudentService.getSurveysByStudent(studentId);
    }

    @PostMapping
    public SurveyDTO createSurvey(@RequestBody Survey survey) {
        return surveyService.createSurvey(survey);
    }

    @PostMapping("/student/{studentId}")
    public SurveyStudent submitSurvey(@PathVariable Long studentId, @RequestBody SurveyStudent surveyStudent) {
        return surveyStudentService.submitSurvey(studentId, surveyStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }
}
