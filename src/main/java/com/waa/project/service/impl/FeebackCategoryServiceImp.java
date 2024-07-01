package com.waa.project.service.impl;

import com.waa.project.entity.FeedbackCategory;
import com.waa.project.repository.FeedbackCategoryRepository;
import com.waa.project.service.FeedbackCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FeebackCategoryServiceImp implements FeedbackCategoryService {

    @Autowired
    private FeedbackCategoryRepository feedbackCategoryRepository;

    @Override
    public List<FeedbackCategory> getAllCategories() {
        return feedbackCategoryRepository.findAll();
    }

    @Override
    public FeedbackCategory getCategory(Long feedId) {
        return feedbackCategoryRepository.findById(feedId).get();
    }

    @Override
    public String save(FeedbackCategory feedback) {
        feedbackCategoryRepository.save(feedback);
        return "FeedbackCategory saved successfully.";
    }

    @Override
    public String update(FeedbackCategory feedback, Long fid) {
        FeedbackCategory feedToUpdate = feedbackCategoryRepository.findById(fid)
                                                                  .orElseThrow(
                                                                          () -> new NoSuchElementException(
                                                                                  "No feedback was found."));
        feedToUpdate.setName(feedback.getName());
        feedToUpdate.setDescription(feedback.getDescription());
        feedbackCategoryRepository.save(feedToUpdate);
        return "FeedbackCategory is updated.";
    }

    @Override
    public String delete(Long fid) {
        feedbackCategoryRepository.deleteById(fid);
        return "FeedbackCategory is deleted.";
    }
}
