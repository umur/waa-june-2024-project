package com.waa.project.service.impl;

import com.waa.project.dto.DiscussionDto;
import com.waa.project.entity.Discussion;
import com.waa.project.entity.DiscussionCategory;
import com.waa.project.repository.DiscussionCategoryRepository;
import com.waa.project.repository.DiscussionRepository;
import com.waa.project.security.contract.AuthUserResponse;
import com.waa.project.service.DiscussionService;
import com.waa.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DiscussionCategoryRepository discussionCategoryRepository;


    @Override
    public Page<DiscussionDto> getDiscussions(Pageable pageable, User user) {
        AuthUserResponse userData = userService.findByUsername(user.getUsername());
        return repository.findAllByStudentId(userData.getId(), pageable)
                         .map(data -> mapper.map(data, DiscussionDto.class));
    }

    @Override
    public DiscussionDto getDiscussionById(long id, User user) {
        AuthUserResponse userData = userService.findByUsername(user.getUsername());
        Discussion discussion = repository.findByIdAndStudentId(id, userData.getId())
                                          .orElseThrow(() -> new RuntimeException(
                                                  "Discussion not found"));

        return mapper.map(discussion, DiscussionDto.class);
    }

    @Override
    public DiscussionDto createDiscussion(DiscussionDto discussionDto, User user) {

        AuthUserResponse userData    = userService.findByUsername(user.getUsername());
        Discussion       requestData = mapper.map(discussionDto, Discussion.class);
        requestData.setStudent(userData.getId());

        DiscussionCategory category = discussionCategoryRepository.findById(discussionDto.getCategory_id())
                                                                  .orElseThrow(() -> new RuntimeException(
                                                                          "Category not found"));
        requestData.setCategory(category);

        Discussion discussion = repository.save(requestData);
        return mapper.map(discussion, DiscussionDto.class);
    }

    @Override
    public DiscussionDto updateDiscussion(long id, DiscussionDto discussionDto, User user) {

        AuthUserResponse userData = userService.findByUsername(user.getUsername());
        Discussion dataById = repository.findByIdAndStudentId(id, userData.getId())
                                        .orElseThrow(() -> new RuntimeException("Discussion not found"));

        Discussion requestData = mapper.map(discussionDto, Discussion.class);
        requestData.setStudent(userData.getId());
        requestData.setId(id);

        DiscussionCategory category = discussionCategoryRepository.findById(discussionDto.getCategory_id())
                                                                  .orElseThrow(() -> new RuntimeException(
                                                                          "Category not found"));
        requestData.setCategory(category);

        Discussion discussion = repository.save(requestData);
        return mapper.map(discussion, DiscussionDto.class);
    }

    @Override
    @Transactional
    public DiscussionDto deleteDiscussion(long id, User user) {
        AuthUserResponse userData = userService.findByUsername(user.getUsername());
        Discussion discussion = repository.findByIdAndStudentId(id, userData.getId())
                                          .orElseThrow(() -> new RuntimeException(
                                                  "Discussion not found"));
        repository.deleteByIdAndStudentId(id, userData.getId());

        return mapper.map(discussion, DiscussionDto.class);
    }

    @Override
    public DiscussionDto searching(String text) {
        System.out.println("Text:" + text);
        Discussion discussion = repository.findAllByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(text, text)
                                          .orElseThrow(() -> new RuntimeException(
                                                  "Data Not found"));
        System.out.println("Output:" + discussion);
        return mapper.map(discussion, DiscussionDto.class);
    }
}
