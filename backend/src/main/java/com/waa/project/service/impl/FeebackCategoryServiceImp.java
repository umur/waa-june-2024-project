package com.waa.project.service.impl;

import com.waa.project.dto.FeedbackCategoryDto;
import com.waa.project.dto.requests.FeedbackCategoryRequest;
import com.waa.project.dto.responses.FeedbackCategoryResponse;
import com.waa.project.entity.FeedbackCategory;
import com.waa.project.exception.ResourceNotFoundException;
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
        feedbackCategoryRepository.findById(feedId).orElseThrow(
                () -> new ResourceNotFoundException("Feedback category not found with id: " + feedId)
        );
        return modelMapper.map(feedbackCategoryRepository.findById(feedId).get(), FeedbackCategoryDto.class);
    }

    @Override
    public FeedbackCategoryResponse save(FeedbackCategoryRequest category) {
        FeedbackCategory feedbackCategory = modelMapper.map(category, FeedbackCategory.class);
        feedbackCategoryRepository.save(feedbackCategory);

        return modelMapper.map(feedbackCategory, FeedbackCategoryResponse.class);
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
    public List<FeedbackCategory> delete(Long fid) {
        feedbackCategoryRepository.findById(fid).orElseThrow(
                () -> new ResourceNotFoundException("Feedback category not found with id: " + fid)
        );
        feedbackCategoryRepository.deleteById(fid);
        return feedbackCategoryRepository.findAll();
    }
}
