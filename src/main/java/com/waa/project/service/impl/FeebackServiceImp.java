package com.waa.project.service.impl;

import com.waa.project.entity.Feedback;
import com.waa.project.repository.FeedbackRepository;
import com.waa.project.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FeebackServiceImp implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedback(Long feedId) {
        return feedbackRepository.findById(feedId).get();
    }

    @Override
    public String save(Feedback feedback) {
        feedbackRepository.save(feedback);
        return "Feedback saved successfully.";
    }

    @Override
    public String update(Feedback feedback, Long fid) {
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
}
