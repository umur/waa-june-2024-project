package com.waa.project.service.impl;

import com.waa.project.dto.FeedbackCategoryDto;
import com.waa.project.entity.FeedbackCategory;
import com.waa.project.repository.FeedbackCategoryRepository;
import com.waa.project.service.FeedbackCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FeebackCategoryServiceImp implements FeedbackCategoryService {

    @Autowired
    private FeedbackCategoryRepository feedbackCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FeedbackCategoryDto> getAllCategories() {
        List<FeedbackCategoryDto> feedbackCategories = new ArrayList<>();
        feedbackCategoryRepository.findAll().forEach(feed -> feedbackCategories.add(modelMapper.map(
                feed, FeedbackCategoryDto.class)));

        return feedbackCategories;
    }

    @Override
    public FeedbackCategoryDto getCategory(Long feedId) {

        return modelMapper.map(feedbackCategoryRepository.findById(feedId), FeedbackCategoryDto.class);
    }

    @Override
    public String save(FeedbackCategoryDto feedback) {
        feedbackCategoryRepository.save(modelMapper.map(feedback, FeedbackCategory.class));
        return "FeedbackCategory saved successfully.";
    }

    @Override
    public String update(FeedbackCategoryDto feedback, Long fid) {
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
