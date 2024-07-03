package com.waa.project.service.impl;

import com.waa.project.dto.DiscussionCategoryDto;
import com.waa.project.entity.DiscussionCategory;
import com.waa.project.repository.DiscussionCategoryRepository;
import com.waa.project.service.DiscussionCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionCategoryServiceImpl implements DiscussionCategoryService {

    @Autowired
    private DiscussionCategoryRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<DiscussionCategoryDto> getDiscussionCategories() {
        List<DiscussionCategory> categories = repository.findAll();

        return categories.stream().map(cat -> mapper.map(cat, DiscussionCategoryDto.class)).toList();
    }
}
