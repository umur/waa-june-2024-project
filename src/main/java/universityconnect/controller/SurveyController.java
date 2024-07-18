package universityconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.AnswerDTO;
import universityconnect.dto.QuestionDTO;
import universityconnect.dto.SurveyDTO;
import universityconnect.dto.SurveyStudentDTO;
import universityconnect.service.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public ResponseEntity<List<SurveyDTO>> getAllSurveys() {
        return ResponseEntity.ok(surveyService.getAllSurveys());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyDTO> getSurveyById(@PathVariable Long id) {
        return ResponseEntity.ok(surveyService.getSurveyById(id));
    }

    @PostMapping
    public ResponseEntity<SurveyDTO> createSurvey(@RequestBody SurveyDTO survey) {
        return ResponseEntity.ok(surveyService.createSurvey(survey));
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }


    @PostMapping("{surveyId}/questions")
    public ResponseEntity<QuestionDTO> createQuestion(@PathVariable Long surveyId, @RequestBody QuestionDTO question) {
        return ResponseEntity.ok(surveyService.createQuestion(surveyId, question));
    }

    @DeleteMapping("{surveyId}/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long surveyId,@PathVariable Long questionId) {
        surveyService.deleteQuestion(surveyId, questionId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{surveyId}/questions")
    public ResponseEntity<?> updateQuestion(@PathVariable Long surveyId, @RequestBody QuestionDTO question) {
        surveyService.updateQuestion(surveyId, question.getId(), question);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{surveyId}/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestionsBySurvey(@PathVariable Long surveyId) {
        return ResponseEntity.ok(surveyService.getQuestionsBySurvey(surveyId));
    }


    @PostMapping("{surveyId}/students/{studentId}/questions/{questionId}/answers")
    public ResponseEntity<?> createAnswer(@PathVariable Long surveyId, @PathVariable Long questionId,
                                          @PathVariable Long studentId, @RequestBody AnswerDTO answer) {
        surveyService.createAnswer(surveyId, questionId, studentId, answer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<List<SurveyStudentDTO>> getSurveysByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(surveyService.getSurveysByStudent(studentId));
    }


}
