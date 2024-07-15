package com.waa.project.service.impl;

import com.waa.project.dto.FeedbackDto;
import com.waa.project.entity.Feedback;
import com.waa.project.entity.FeedbackCategory;
import com.waa.project.entity.Student;
import com.waa.project.entity.User;
import com.waa.project.repository.FeedbackCategoryRepository;
import com.waa.project.repository.FeedbackRepository;
import com.waa.project.repository.StudentRepository;
import com.waa.project.repository.UserRepository;
import com.waa.project.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserRepository userRepository;

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
    public String save(FeedbackDto dto, Long userId) {
        Feedback feedbackToSave = modelMapper.map(dto, Feedback.class);

        FeedbackCategory feedbackCategory =
                feedbackCategoryRepository.findById(dto.getFeedbackCategory().getId()).get();
        feedbackToSave.setCategory(feedbackCategory);

        if (userId != null) {
            User user = userRepository.findById(userId).get();
            if (!user.getRoleType().equals("ADMIN")) {
                Student student = studentRepository.findById(userId).get();
                feedbackToSave.setStudent(student);
            }
        }

        feedbackRepository.save(feedbackToSave);
        return "Feedback saved successfully.";
    }

    @Override
    public String update(FeedbackDto feedback, Long fid, Long userId) {
        Feedback feedToUpdate = feedbackRepository.findById(fid).orElse(null);
        if (feedToUpdate == null) {
            return "Feedback not found.";
        }

        if (userId != null) {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return "Anonymous Feedback cannot be updated.";
            }

            var result = feedToUpdate.getStudent();
            if (result != null) {
                System.out.println("role ===" + user.getRoleType());
                System.out.println("stud Id ===" + result.getId() + " ===" + userId);
                Long studentId = result.getId();

                if (userId.equals(studentId)) {
                    Student student = studentRepository.findById(userId).orElse(null);
                    if (student == null) {
                        return "Student not found.";
                    }

                    feedToUpdate.setStudent(student);
                    feedToUpdate.setTitle(feedback.getTitle());
                    feedToUpdate.setBody(feedback.getBody());
                    feedbackRepository.save(feedToUpdate);
                    return "Feedback is updated.";
                }
            } else {
                return "Anonymous Feedback cannot be updated.";
            }
        }
        return "You cannot update other's Feedback.";
    }

    @Override
    public String delete(Long fid) {
        feedbackRepository.deleteById(fid);
        return "Feedback is deleted.";
    }

    @Override
    public List<FeedbackDto> findFeedbackByCategory(Long id) {
        List<FeedbackDto> list = new ArrayList<>();
        List<Feedback> feedback = feedbackRepository.findFeedbackByCategoryId(id);
        feedback.forEach(feed -> list.add(modelMapper.map(feed, FeedbackDto.class)));
        return list;
    }

    @Override
    public List<Object[]> findFeedbackByCategoryCount() {
        return feedbackRepository.findFeedbackByCategoryCount();
    }

    @Override
    public List<FeedbackDto> search(String title) {
        List<Feedback> result = feedbackRepository.findFeedbackByCategoryName(title);
        List<FeedbackDto> res = new ArrayList<>();
        result.forEach(f -> res.add(modelMapper.map(f, FeedbackDto.class)));
        return res;
    }

    @Override
    public List<FeedbackDto> findByTitleAndId(String title, Long id) {
        List<FeedbackDto> list = new ArrayList<>();
        List<Feedback> feedbacks = feedbackRepository.findFeedbackByTitleContainingAndIdIsGreaterThan(title, id);
        feedbacks.forEach(f -> list.add(modelMapper.map(f, FeedbackDto.class)));
        return list;
    }
}
