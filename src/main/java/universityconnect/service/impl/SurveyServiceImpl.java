package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.*;
import universityconnect.dto.AnswerDTO;
import universityconnect.dto.QuestionDTO;
import universityconnect.dto.SurveyDTO;
import universityconnect.dto.SurveyStudentDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.AnswerMapper;
import universityconnect.mapper.QuestionMapper;
import universityconnect.mapper.SurveyMapper;
import universityconnect.mapper.SurveyStudentMapper;
import universityconnect.repository.*;
import universityconnect.service.SurveyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final StudentRepository studentRepository;

    private final SurveyStudentRepository surveyStudentRepository;

    private final SurveyMapper surveyMapper;

    private final QuestionMapper questionMapper;

    private final AnswerMapper answerMapper;

    private final SurveyStudentMapper surveyStudentMapper;


    @Override
    public SurveyDTO createSurvey(SurveyDTO survey) {
        Survey reqSurvey = surveyMapper.surveyDTOToSurvey(survey);
        Survey s = surveyRepository.saveAndFlush(reqSurvey);
        return surveyMapper.surveyToSurveyDTO(s);

    }

    @Override
    public QuestionDTO createQuestion(Long surveyId, QuestionDTO question) {
        Question reqQ = questionMapper.questionDTOToQuestion(question);

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey with id " + surveyId + " does not exist"));
        survey.getQuestions().add(reqQ);
        reqQ.setSurvey(survey);

        Question repQ = questionRepository.save(reqQ);

        return questionMapper.questionToQuestionDTO(repQ);
    }

    @Override
    public List<QuestionDTO> getQuestionsBySurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey with id " + surveyId + " does not exist"));


        return survey.getQuestions().stream()
                .map(questionMapper::questionToQuestionDTO)
                .collect(Collectors.toList());


    }

    @Override
    public void createAnswer(Long surveyId, Long questionId, Long studentId, AnswerDTO answer) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question with id " + questionId + " does not exist"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " does not exist"));

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey with id " + surveyId + " does not exist"));


        Answer reqAnswer = answerMapper.answerDTOToAnswer(answer);
        question.getAnswers().add(reqAnswer);

        answerRepository.save(reqAnswer);
        questionRepository.save(question);

        SurveyStudent surveyStudent = surveyStudentRepository.findBySurveyIdAndStudentId(surveyId, studentId)
                .orElseGet(() -> new SurveyStudent(survey, student));

        if (surveyStudent.getAnswers() == null) {
            surveyStudent.setAnswers(new ArrayList<>());
        }

        surveyStudent.getAnswers().add(reqAnswer);

        surveyStudentRepository.save(surveyStudent);

    }

    @Override
    public List<SurveyStudentDTO> getSurveysByStudent(Long studentId) {

        return surveyStudentRepository.findByStudentId(studentId)
                .stream()
                .map(surveyStudentMapper::surveyStudentToSurveyStudentDTO)
                .collect(Collectors.toList());


    }

    @Override
    public void deleteQuestion(Long surveyId, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question with id " + questionId + " does not exist"));

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey with id " + surveyId + " does not exist"));

        survey.getQuestions().remove(question);
        questionRepository.delete(question);
    }

    @Override
    public void updateQuestion(Long surveyId, Long questionId, QuestionDTO question) {
        Question reqQ = questionMapper.questionDTOToQuestion(question);

        Question questionToUpdate = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question with id " + questionId + " does not exist"));

        questionToUpdate.setTitle(reqQ.getTitle());
        questionRepository.save(questionToUpdate);
    }

    @Override
    public void deleteSurvey(Long id) {

        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Survey with id " + id + " does not exist"));
        survey.getQuestions().forEach(question -> {
            answerRepository.deleteAll(question.getAnswers());
            questionRepository.delete(question);
            surveyStudentRepository.deleteAll(surveyStudentRepository.findBySurveyId(id));
        });

        surveyRepository.deleteById(id);
    }

    @Override
    public SurveyDTO getSurveyById(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey with id " + id + " does not exist"));
        return surveyMapper.surveyToSurveyDTO(survey);
    }

    @Override
    public List<SurveyDTO> getAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();

        return surveys.stream()
                .map(surveyMapper::surveyToSurveyDTO)
                .collect(Collectors.toList());
    }


}
