package com.waa.project.service.impl;

import com.waa.project.dto.FeedbackDto;
import com.waa.project.entity.Feedback;
import com.waa.project.entity.FeedbackCategory;
import com.waa.project.entity.Student;
import com.waa.project.repository.FeedbackCategoryRepository;
import com.waa.project.repository.FeedbackRepository;
import com.waa.project.repository.StudentRepository;
import com.waa.project.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FeebackServiceImp implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackCategoryRepository feedbackCategoryRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        List<FeedbackDto> list = new ArrayList<>();
        feedbackRepository.findAll().forEach(feed -> list.add(modelMapper.map(feed, FeedbackDto.class)));
        return list;
    }

    @Override
    public FeedbackDto getFeedback(Long feedId) {
        Feedback feedback = feedbackRepository.findById(feedId).get();
        return modelMapper.map(feedback, FeedbackDto.class);
    }

    @Override
    public String save(FeedbackDto feedback) {
        Feedback         feedbackToSave   = modelMapper.map(feedback, Feedback.class);
        FeedbackCategory feedbackCategory = feedbackCategoryRepository.findById(1L).get();
        feedbackToSave.setCategory(feedbackCategory);

        Student student = studentRepository.findById(1L).get();
        feedbackToSave.setStudent(student);

        feedbackRepository.save(feedbackToSave);
        return "Feedback saved successfully.";
    }

    @Override
    public String update(FeedbackDto feedback, Long fid) {
        Feedback feedToUpdate = feedbackRepository.findById(fid)
                                                  .orElseThrow(
                                                          () -> new NoSuchElementException("No feedback was found."));
        feedToUpdate.setTitle(feedback.getTitle());
        feedToUpdate.setBody(feedback.getBody());
        feedbackRepository.save(feedToUpdate);
        return "Feedback is updated.";
    }

    @Override
    public String delete(Long fid) {
        feedbackRepository.deleteById(fid);
        return "Feedback is deleted.";
    }

    @Override
    public List<FeedbackDto> findFeedbackByCategory(Long id) {
        List<FeedbackDto> list     = new ArrayList<>();
        List<Feedback>    feedback = feedbackRepository.findFeedbackByCategoryId(id);
        feedback.forEach(feed -> list.add(modelMapper.map(feed, FeedbackDto.class)));
        return list;
    }

    @Override
    public List<Object[]> findFeedbackByCategoryCount() {
        return feedbackRepository.findFeedbackByCategoryCount();
    }
}
